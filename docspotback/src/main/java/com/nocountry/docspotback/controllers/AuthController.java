package com.nocountry.docspotback.controllers;

import java.util.*;

import com.nocountry.docspotback.dto.RegisterRequest;
import com.nocountry.docspotback.dto.RoleDTO;
import com.nocountry.docspotback.dto.UserDTO;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Role;
import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.services.IRoleService;
import com.nocountry.docspotback.services.impl.PatientServiceImpl;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;
import com.nocountry.docspotback.services.impl.RoleServiceImpl;
import com.nocountry.docspotback.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autentificación y Registro", description = "Registro de usuario según roles y Autentificación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ProfessionalServiceImpl professionalService;
    
    @Autowired
    private PatientServiceImpl patientlService;
    
    @Autowired
    private RoleServiceImpl roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Operation(
    	      summary = "Registra al Usuario según rol(ROLE_ADMIN,ROLE_PATIENT,ROLE_PROFESSIONAL)",
    	      description = "User necesita que se envie email,password,Lista de roles(roles),patient o professional",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = UserDTO.class),mediaType = "x-www-form-urlencoded")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(value = "/register",consumes="application/x-www-form-urlencoded")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest userDto) {
        Map<String, String> body = new HashMap<>();

        try {
            User user = new User();
            user.setEmail(userDto.getEmail());
            String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

            user.setPassword(encryptedPassword);
            user.setActive(true);

            List<Role> roles = new ArrayList<>();
            Role role1 = new Role();
            role1 =mapper.map(roleService.findRoleByroleName(userDto.getNameRole()),Role.class);
            roles.add(role1);
           
            user.setIdUser(UUID.randomUUID());
            user.setRoles(roles);
            String roleName="";
            for (int i=0;i<1;i++) {
                 roleName = roles.get(i).getNameRole();
                System.out.println(roleName);
                if (roleName.equals("ROLE_PATIENT")){
                    Patient patient = new Patient();
                    patient.setIdPatient(UUID.randomUUID());
                    patient.setNamePatient(userDto.getNameUser());
                    patient.setPhotoPatient(userDto.getPhotoPatient());
                    patient.setCellphonePatient(userDto.getCellphonePatient());
                    patient.setHasSocialWork(userDto.getHasSocialWork());
                    patient.setSocialWork(userDto.getSocialWork());
                    patient.setUser(user);
                    user.setPatient(patient);
                    service.save(user);
                    patientlService.save(patient);
                    body.put("message", "Patient registered successfully!");
                    return ResponseEntity.ok(body);
                } else if(roleName.equals("ROLE_PROFESSIONAL")){
                    Professional professional = new Professional();
                    professional.setIdProfessional(UUID.randomUUID());
                    professional.setNameProfessional(userDto.getNameUser());
                    professional.setMp(userDto.getMp());
                    professional.setReputation(0.0);
                    professional.setValueQuery(userDto.getValueQuery());
                    professional.setUser(user);
                    user.setProfessional(professional);
                    service.save(user);
                    professionalService.save(professional);
                    body.put("message", "Professional registered successfully!");
                    return ResponseEntity.ok(body);
                }
           
            }
            service.save(user);
            body.put("message", "Admin registered successfully!");
            return ResponseEntity.ok(body);
        } catch (NoSuchElementException e) {
            body.put("message", "Error: Element not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } catch (DataIntegrityViolationException e) {
            body.put("message", "Error: Data integrity violation!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        } catch (Exception e) {
            body.put("message", "User registered successfully!");
            return ResponseEntity.ok(body);
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
}
