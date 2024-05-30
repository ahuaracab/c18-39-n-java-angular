package com.nocountry.docspotback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalSpecialtyDTO {
    private ProfessionalDTO professional;
    private SpecialtyDTO specialty;
}
