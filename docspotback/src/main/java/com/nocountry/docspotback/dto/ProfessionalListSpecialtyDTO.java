package com.nocountry.docspotback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalListSpecialtyDTO {
    @NotNull
    private ProfessionalDTO consult;

    @NotNull
    private List<SpecialtyDTO> listSpecialty;
}
