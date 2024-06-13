package com.nocountry.docspotback.dto;

import java.util.UUID;

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

    private String urlPhoto;
    
    @Schema(example = "70.50")
    private Double valueQuery;

    @Schema(example = "8w5w8f8g452")
    private String mp;

    @Schema(example = "d421ed25-c472-4e7f-9694-e86e051132ee")
    private UUID specialty1;
    @Schema(example = "c629bec5-3ea7-482f-84a0-d78b3e968de3")
    private UUID specialty2;
}
