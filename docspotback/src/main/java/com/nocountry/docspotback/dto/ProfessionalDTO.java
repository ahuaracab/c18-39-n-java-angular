package com.nocountry.docspotback.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDTO {

    private UUID idProfessional;

    @NotNull
    private String nameProfessional;

    @NotNull
    private Double reputation;

    @NotNull
    private Double valueQuery;

    @NotNull
    private String mp;

    @NotNull
    @JsonIgnore
    private UserDTO user;

    @NotNull
    private List<SpecialtyDTO> specialties;

    @NotNull
    private List<ShiftDTO> shifts;
}
