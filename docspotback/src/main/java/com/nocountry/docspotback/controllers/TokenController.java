package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.services.impl.KeyCloakServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {


    @Autowired
    private KeyCloakServiceImpl keycloakService;


    // KeyCloak
    @PostMapping(value = "/user/add")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) throws Exception {
        boolean rpta = keycloakService.addUser(user);
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }

}
