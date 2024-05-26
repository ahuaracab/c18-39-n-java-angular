    package com.nocountry.docspotback.security;


    import io.jsonwebtoken.security.Keys;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.util.matcher.RequestMatcher;

    import java.security.Key;

    @Configuration
    @EnableWebSecurity
    public class KeycloakConfig {

        @Value("${keycloak.realm}")
        private String realm;

        @Value("${keycloak.auth-server-url}")
        private String authServerUrl;

        @Value("${keycloak.clientId}")
        private String clientId;

        @Value("${keycloak.credentials.secret}")
        private String secret;

        @Bean
        public Key key() {
            return Keys.hmacShaKeyFor(secret.getBytes());
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
            http.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/login/**").permitAll()
                    .requestMatchers("/tokens/users/**").permitAll()
                    .requestMatchers("/patient/**").hasRole("PATIENT")
                    .anyRequest().authenticated());
            return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return new KeycloakUserDetailsService(realm, authServerUrl, clientId, secret);
        }
    }
