package com.nocountry.docspotback.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class KeycloakUserDetailsService implements UserDetailsService {

    private final String realm;
    private final String authServerUrl;
    private final String resource;
    private final String secret;

    public KeycloakUserDetailsService(String realm, String authServerUrl, String resource, String secret) {
        this.realm = realm;
        this.authServerUrl = authServerUrl;
        this.resource = resource;
        this.secret = secret;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementar la lógica para obtener el usuario de Keycloak
        // Por ejemplo, utilizando la API de Keycloak
        // ...
        return new User(username, "", Collections.emptyList());
    }
}