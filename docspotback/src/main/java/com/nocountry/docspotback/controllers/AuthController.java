package com.nocountry.docspotback.controllers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nocountry.docspotback.dto.AuthDTO;
import com.nocountry.docspotback.dto.AuthUser;
import com.nocountry.docspotback.dto.RegisterRequest;
import com.nocountry.docspotback.dto.UserDTO;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Role;
import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.repositories.IUserRepo;
import com.nocountry.docspotback.security.filter.JwtAuthenticationFilter;
import com.nocountry.docspotback.security.filter.JwtValidationFilter;
import com.nocountry.docspotback.services.AuthService;
import com.nocountry.docspotback.services.impl.PatientServiceImpl;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;
import com.nocountry.docspotback.services.impl.RoleServiceImpl;
import com.nocountry.docspotback.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Autentificación y Registro", description = "Registro de usuario según roles y Autentificación")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http:localhost:4200")
public class AuthController {
	
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ProfessionalServiceImpl professionalService;
    
    @Autowired
    private PatientServiceImpl patientService;
    
    @Autowired
    private RoleServiceImpl roleService;
    
    @Autowired
    private IUserRepo userRepo;
    
    @Autowired AuthService authService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

/*    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

*/
    

    //@Autowired
    //private JwtValidationFilter jwtValidationFilter;
    @Operation(
    	    summary = "Registra al Usuario según rol(ROLE_ADMIN,ROLE_PATIENT,ROLE_PROFESSIONAL)",
    	    description = "User necesita que se envie email,password,Lista de roles(roles),patient o professional",
    	    tags = {}
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = UserDTO.class),mediaType = "x-www-form-urlencoded")} ),
    	    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
    	    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) } )
    	})
    	@PostMapping(value = "/register",consumes="application/x-www-form-urlencoded")
    	public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest userDto) {
    	    Map<String, String> body = new HashMap<>();

    	    ExecutorService executor = Executors.newFixedThreadPool(3);
            List<Role> roles = new ArrayList<>();
            Role role1 = new Role();
            role1 = mapper.map(roleService.findRoleByroleName(userDto.getNameRole()), Role.class);
            roles.add(role1);
    	    try {
    	        // Tarea 1: Crear el usuario
    	        Callable<User> createUserTask = () -> {
    	            User user = new User();
    	            user.setEmail(userDto.getEmail());
    	            String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
    	            user.setPassword(encryptedPassword);
    	            user.setActive(true);
    	            user.setIdUser(UUID.randomUUID());
    	            user.setRoles(roles);
    	            return service.save(user); // Save the user to the database
    	        };

    	        Future<User> userFuture = executor.submit(createUserTask);

    	        // Tarea 2: Crear el paciente o profesional según el rol
    	        Callable<Object> createRoleTask = () -> {


    	            String roleName = roles.get(0).getNameRole();
    	            Object roleObject = null;

    	            if (roleName.equals("ROLE_PATIENT")) {
    	                Patient patient = new Patient();
    	                patient.setIdPatient(UUID.randomUUID());
    	                patient.setNamePatient(userDto.getNameUser());
    	                patient.setPhotoPatient(userDto.getPhotoPatient());
    	                patient.setCellphonePatient(userDto.getCellphonePatient());
    	                patient.setHasSocialWork(userDto.getHasSocialWork());
    	                patient.setSocialWork(userDto.getSocialWork());
    	                roleObject = patient;
    	            } else if (roleName.equals("ROLE_PROFESSIONAL")) {
    	                Professional professional = new Professional();
    	                professional.setIdProfessional(UUID.randomUUID());
    	                professional.setNameProfessional(userDto.getNameUser());
    	                professional.setMp(userDto.getMp());
    	                professional.setReputation(0.0);
    	                professional.setValueQuery(userDto.getValueQuery());
    	                roleObject = professional;
    	            }

    	            return roleObject;
    	        };

    	        Future<Object> roleFuture = executor.submit(createRoleTask);

    	        // Tarea 3: Actualizar el usuario con el paciente o profesional creado
    	        Callable<Void> updateUserTask = () -> {
    	            User user = userFuture.get();
    	            Object roleObject = roleFuture.get();

    	            if (roleObject instanceof Patient) {
    	                Patient patient = (Patient) roleObject;
    	                user.setPatient(patient);
    	                patient.setUser(user);
    	                patientService.save(patient);
    	            } else if (roleObject instanceof Professional) {
    	                Professional professional = (Professional) roleObject;
    	                user.setProfessional(professional);
    	                professional.setUser(user);
    	                professionalService.save(professional);
    	            }

    	            return null;
    	        };

    	        executor.submit(updateUserTask).get();

    	        body.put("message", "User registered successfully!");
    	        return ResponseEntity.ok(body);
    	    } catch (InterruptedException | ExecutionException e) {
    	        body.put("message", "Error registering user!"+e.getMessage().toString());
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    	    } finally {
    	        executor.shutdown();
    	    }
    	}
    
    @Operation(
  	      summary = "Obtine datos de un usuario por email como parámetro",
  	      description = "User necesita que se envie atributo email",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = UserDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> userByEmail(@PathVariable("email")@Parameter(name = "email", description = "Email del User", example = "luiscordova@gmail.com") String email ) {
        Optional<User> response = service.findByEmail(email);
        if(response.isPresent()) {
            User user = response.get();
            Patient patient = service.getPatientByUserId(user.getIdUser());
            Professional professional = service.getProfessionalByUserId(user.getIdUser());
            if(patient!=null) {
                user.setPatient(patient);
            }else if(professional!=null) {
            	user.setProfessional(professional);
            }
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
 /*   @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            jwtAuthenticationFilter.successfulAuthentication(null, null, null, authentication);
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException | IOException | ServletException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }*/
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequest userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.username(),
                                userLogin.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUser userDetails = (AuthUser) authentication.getPrincipal();
        System.out.println(userDetails);
        Optional<User> user = userRepo.findByEmail(userLogin.username());
        log.info("Token requested for user :{}", authentication.getAuthorities());
        String token = authService.generateToken(authentication);

        AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token,user);
  
        return ResponseEntity.ok(response);
    }
}
