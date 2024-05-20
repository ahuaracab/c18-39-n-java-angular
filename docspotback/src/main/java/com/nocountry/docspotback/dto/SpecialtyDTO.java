package com.nocountry.docspotback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    private UUID idSpecialty;

    @NotNull
    private String nameSpecialty;

    @NotNull
    private String descriptionSpecialty;
}
