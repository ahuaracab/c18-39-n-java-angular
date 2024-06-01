package com.nocountry.docspotback.controllers;

import java.util.*;


import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.dto.RoleDTO;
import com.nocountry.docspotback.dto.UserDTO;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Role;
import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDto) {
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
                role.setNameRole(roleDto.getNameRole());
                roleName=role.getNameRole();
                role.setDescriptionRole(roleDto.getDescriptionRole());
                roles.add(role);
            }
            user.setIdUser(UUID.randomUUID());
            user.setRoles(roles);

            if (roleName.equals("PATIENT")){
                Patient patient = new Patient();
                patient.setIdPatient(UUID.randomUUID());
                patient.setNamePatient(userDto.getPatients().getNamePatient());
                patient.setPhotoPatient(userDto.getPatients().getPhotoPatient());
                patient.setCellphonePatient(userDto.getPatients().getCellphonePatient());
                patient.setHasSocialWork(userDto.getPatients().getHasSocialWork());
                patient.setSocialWork(userDto.getPatients().getSocialWork());
                patient.setUser(user);
                user.setPatient(patient);
            }else if(roleName.equals("PROFESSIONAL")){
                Professional professional = new Professional();
                professional.setIdProfessional(UUID.randomUUID());
                professional.setNameProfessional(userDto.getProfessional().getNameProfessional());
                professional.setMp(userDto.getProfessional().getMp());
                professional.setReputation(0.0);
                professional.setValueQuery(userDto.getProfessional().getValueQuery());
                professional.setUser(user);
                user.setProfessional(professional);
            }

            service.save(user);

            Map<String, String> body = new HashMap<>();
            body.put("message", "User registered successfully!");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "Error registering user!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Optional<User>> listReservationsByProfessional(@PathVariable("email") String email) {
        Optional<User> response = service.findByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
