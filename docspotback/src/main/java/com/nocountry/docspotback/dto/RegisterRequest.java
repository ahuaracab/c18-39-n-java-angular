package com.nocountry.docspotback.dto;

import org.hibernate.validator.constraints.UniqueElements;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotNull
    @UniqueElements
    @Schema(example = "luiscordova@gmail.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Schema(example = "luis123",requiredMode = RequiredMode.REQUIRED)
    private String password;

    @NotNull
    @Schema(example = "ROLE_PATIENT",requiredMode = RequiredMode.REQUIRED)
    private String nameRole;

    @Schema(example = "Luis Cordova",requiredMode = RequiredMode.REQUIRED)
    private String nameUser;

    @Schema(example = "98564726")
    private String cellphonePatient;

    @Schema(example = "https://robohash.org/bd6da376-037a-458a-97bb-bb7ff5f62c07")
    private String photoPatient;

    @Schema(example = "true")
    private Boolean hasSocialWork;

    @Schema(example = "258eg9e7")
    private String socialWork;
    
    
    @Schema(example = "70.50")
    private Double valueQuery;

    @Schema(example = "8w5w8f8g452")
    private String mp;

}
