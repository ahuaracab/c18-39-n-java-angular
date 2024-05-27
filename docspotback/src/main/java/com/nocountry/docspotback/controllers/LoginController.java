package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.models.ResetToken;
import com.nocountry.docspotback.security.KeyCloakConfig;
import com.nocountry.docspotback.services.ILoginService;
import com.nocountry.docspotback.services.IResetTokenService;

import com.nocountry.docspotback.util.EmailUtil;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private ILoginService service;

    @Autowired
    private IResetTokenService tokenService;

    @Autowired
    private EmailUtil emailUtil;



    //KeyCloak
    @PostMapping(value = "/sendMail", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Integer> sendMailKeycloak(@RequestBody String username) throws Exception {
        UsersResource usersResource = KeyCloakConfig.getInstance(username).realm(KeyCloakConfig.realm).users();
        List<UserRepresentation> lista = usersResource.search(username, true);
        boolean rpta = lista.isEmpty();

        if (!rpta) {
            //Si lista no vacia, significa que usuario existe, entonces enviar correo
            UserRepresentation user = lista.get(0);
            usersResource.get(user.getId()).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD")); //.resetPasswordEmail();
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
        return new ResponseEntity<>(0, HttpStatus.OK);
    }

    @GetMapping(value = "/reset/check/{token}")
    public ResponseEntity<Integer> checkToken(@PathVariable("token") String token) {
        int rpta = 0;
        try {
            if (token != null && !token.isEmpty()) {
                ResetToken rt = tokenService.findByToken(token);
                if (rt != null && rt.getId().equals(UUID.randomUUID())) {
                    if (!rt.isExpired()) {
                        rpta = 1;
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }

    @PostMapping(value = "/reset/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> resetPassword(@PathVariable("token") String token, @RequestBody String password) {
        try {
            ResetToken rt = tokenService.findByToken(token);
            service.changePassword(password, rt.getUser().getEmail());
            tokenService.delete(rt);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // KeyCloak
    @PostMapping("/logout")
    public void logout(@RequestBody String username) {
        UsersResource usersResource = KeyCloakConfig.getInstance(username).realm(KeyCloakConfig.realm).users();
        UserRepresentation user = usersResource.search(username, true).get(0);
        usersResource.get(user.getId()).logout();
    }

}
