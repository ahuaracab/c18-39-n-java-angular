package com.nocountry.docspotback.controllers;

import java.util.*;

import com.nocountry.docspotback.dto.RoleDTO;
import com.nocountry.docspotback.dto.UserDTO;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Role;
import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.services.impl.PatientServiceImpl;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;
import com.nocountry.docspotback.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDto) {
        Map<String, String> body = new HashMap<>();

        String roleName="";
        try {
            User user = new User();
            user.setEmail(userDto.getEmail());
            String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

            user.setPassword(encryptedPassword);
            user.setActive(true);

            List<Role> roles = new ArrayList<>();
            for (RoleDTO roleDto : userDto.getRoles()) {
                Role role = new Role();
                role.setIdRole(roleDto.getIdRole());
                roles.add(role);
            }
            user.setIdUser(UUID.randomUUID());
            user.setRoles(roles);

            if (roleName.equals("PATIENT")){
                Patient patient = new Patient();
                patient.setIdPatient(UUID.randomUUID());
                patient.setNamePatient(userDto.getPatient().getNamePatient());
                patient.setPhotoPatient(userDto.getPatient().getPhotoPatient());
                patient.setCellphonePatient(userDto.getPatient().getCellphonePatient());
                patient.setHasSocialWork(userDto.getPatient().getHasSocialWork());
                patient.setSocialWork(userDto.getPatient().getSocialWork());
                patient.setUser(user);
                user.setPatient(patient);
                service.save(user);
                patientlService.save(patient);
                body.put("message", "User registered successfully!");
                return ResponseEntity.ok(body);
            }else if(roleName.equals("PROFESSIONAL")){
                Professional professional = new Professional();
                professional.setIdProfessional(UUID.randomUUID());
                professional.setNameProfessional(userDto.getProfessional().getNameProfessional());
                professional.setMp(userDto.getProfessional().getMp());
                professional.setReputation(0.0);
                professional.setValueQuery(userDto.getProfessional().getValueQuery());
                professional.setUser(user);
                user.setProfessional(professional);
                service.save(user);
                professionalService.save(professional);
                body.put("message", "User registered successfully!");
                return ResponseEntity.ok(body);
            }
            service.save(user);
            body.put("message", "User registered successfully!");
            return ResponseEntity.ok(body);
        } catch (NoSuchElementException e) {
            body.put("message", "Error: Element not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } catch (DataIntegrityViolationException e) {
            body.put("message", "Error: Data integrity violation!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        } catch (Exception e) {
            body.put("message", "Error registering user!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> userByEmail(@PathVariable("email") String email) {
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
