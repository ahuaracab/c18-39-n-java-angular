package com.nocountry.docspotback.controllers;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de inicio de sesión que maneja las solicitudes de inicio de sesión y devuelve un token de autenticación.
 */
@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    /**
     * Constructor que inyecta el administrador de autenticación.
     *
     * @param authenticationManager administrador de autenticación
     */
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Método que maneja la solicitud de inicio de sesión y devuelve un token de autenticación.
     *
     * @param credentials credenciales de inicio de sesión (nombre de usuario y contraseña)
     * @return respuesta con el token de autenticación o un estado de error
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Verificar si las credenciales están vacías
        if (username == null || password == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Autenticar las credenciales con el administrador de autenticación
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar un token de autenticación con la información del usuario autenticado
            String token = Jwts.builder()
                    .setSubject(authentication.getName())
                    .signWith(getSecretKey())
                    .compact();

            // Devolver la respuesta con el token de autenticación
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            // Devolver un estado de error si la autenticación falla
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Método que devuelve la clave secreta utilizada para firmar el token de autenticación.
     *
     * @return clave secreta
     */
    private Key getSecretKey() {
        String secretKeyString = "swd52PGI$el6/w2qw(q6256wq2q6dqw-wwq65q46qw6rg#44#%$ff9sr=r3";
        return new SecretKeySpec(secretKeyString.getBytes(), "HmacSHA256");
    }
}