package com.nocountry.docspotback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    private UUID idSpecialty;

    @Schema(example = "Ginecología", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private String nameSpecialty;

    @NotNull
    @Schema(example = "Atención de las mujeres durante el embarazo y el parto, y en el diagnóstico y tratamiento de enfermedades de los órganos reproductivos femeninos", requiredMode = RequiredMode.REQUIRED)
    private String descriptionSpecialty;
}
