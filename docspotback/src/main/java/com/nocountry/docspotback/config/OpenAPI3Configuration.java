package com.nocountry.docspotback.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPI3Configuration {
	
    Contact contact = new Contact().name("c18-39-n-java-angular").email("docspot@gmail.com") .url("https://github.com/ahuaracab/c18-39-n-java-angular/tree/main/docspotback");
    
    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
    
    Server devServer = new Server().
    url("http://localhost:8000").
    description("Server URL in Development environment");

    Server prodServer = new Server().
    url("https://docspotback.onrender.com").
    description("Server URL in Production environment");
    
    Info info = new Info()
            .title("Doc Spot API")
            .version("1.0")
            .contact(contact)
            .description("Esta API contiene endpoints para la generación del aplicativo web DocSpot.").termsOfService("https://www.bezkoder.com/terms")
            .license(mitLicense);
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                              )).info(info).servers(List.of(prodServer,devServer));
    }
}