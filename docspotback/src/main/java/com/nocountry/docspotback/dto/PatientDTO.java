package com.nocountry.docspotback.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {


    private UUID idPatient;

    @Schema(example = "Carlos Quispe",requiredMode = RequiredMode.REQUIRED)
    private String namePatient;
    
    @Schema(example = "987535878")
    private String cellphonePatient;
    
    @Schema(example = "https://robohash.org/bd6da376-037a-458a-97bb-bb7ff5f62c07")
    private String photoPatient;

    @Schema(example = "true",requiredMode = RequiredMode.REQUIRED)
    private Boolean hasSocialWork;

    @Schema(example = "855858f")
    private String socialWork;
    
    @JsonIgnore
    private UserDTO user;
}
