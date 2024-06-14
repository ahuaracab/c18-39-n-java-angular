package com.nocountry.docspotback.services;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.nocountry.docspotback.security.TokenJwtConfig.*;

//@Service
public class AuthService {
	/*
    @Autowired
    private JwtEncoder jwtEncoder;


    public String generateToken(Authentication authentication) {

    	


        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication
                .getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        		
        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .add("username", username)
                .build();

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
        return token;
        Instant now = Instant.now();
        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        // Obtener los roles del usuario
     List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                //.map(authority -> authority.substring(5)) // remover el prefijo "ROLE_"
                .collect(Collectors.toList());

        
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("roles", roles) // Agrega esta línea
                .build();
                return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

     
        
    }
*/
}