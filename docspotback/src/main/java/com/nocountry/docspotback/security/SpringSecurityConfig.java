package com.nocountry.docspotback.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.nocountry.docspotback.security.filter.JwtAuthenticationFilter;
import com.nocountry.docspotback.services.impl.JpaUserDetailsService;


import com.nocountry.docspotback.security.filter.JwtValidationFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity( prePostEnabled = true)
public class SpringSecurityConfig {
	  
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

  @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
  }
   
	
    @Autowired
    private JpaUserDetailsService userDetailsService;



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        		 .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authz) -> authz
                		 .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/health").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/professional","/api/professional/pageable/**","/api/professional/**","/api/professional/pageable").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/professional").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/professional-specialties/professional/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/patients").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/professional").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/shifts","/api/shifts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/specialty","/api/specialty/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/shifts").permitAll()
     
                        .anyRequest().authenticated())
                .addFilter(jwtAuthorizationFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                //.oauth2ResourceServer((oauth2) -> oauth2.jwt((jwt) -> jwt.decoder(jwtDecoder())))
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults())
                /*.httpBasic(c -> c.authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpStatus.SC_UNAUTHORIZED)))*/
                .csrf(config -> config.disable())
                //.cors(cors -> cors.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("*"));        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public JwtAuthenticationFilter jwtAuthorizationFilter() throws Exception {
       JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
       jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
        return jwtAuthenticationFilter;
}
}