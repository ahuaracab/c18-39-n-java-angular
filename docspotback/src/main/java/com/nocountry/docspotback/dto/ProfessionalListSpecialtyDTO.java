package com.nocountry.docspotback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalListSpecialtyDTO {
    @NotNull
    @JsonIgnore
    private ProfessionalDTO professional;

    @NotNull
    private List<SpecialtyDTO> listSpecialty;

    public ProfessionalDTO getProfessional() {
        return professional;
    }

    public void setProfessional(ProfessionalDTO professional) {
        this.professional = professional;
    }

    public List<SpecialtyDTO> getListSpecialty() {
        return listSpecialty;
    }

    public void setListSpecialty(List<SpecialtyDTO> listSpecialty) {
        this.listSpecialty = listSpecialty;
    }
}
