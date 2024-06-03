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
    private ProfessionalDTO consult;

    @NotNull
    private List<SpecialtyDTO> listSpecialty;

    public ProfessionalDTO getConsult() {
        return consult;
    }

    public void setConsult(ProfessionalDTO consult) {
        this.consult = consult;
    }

    public List<SpecialtyDTO> getListSpecialty() {
        return listSpecialty;
    }

    public void setListSpecialty(List<SpecialtyDTO> listSpecialty) {
        this.listSpecialty = listSpecialty;
    }
}
