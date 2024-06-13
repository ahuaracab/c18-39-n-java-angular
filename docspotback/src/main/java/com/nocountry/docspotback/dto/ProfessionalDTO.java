package com.nocountry.docspotback.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDTO {

    private UUID idProfessional;

    @NotNull
    @Schema(example = "Pablo Matos")
    private String nameProfessional;

    @NotNull
    private Double reputation;

    private String urlPhoto;
    
    @NotNull
    @Schema(example = "79.9")
    private Double valueQuery;

    @NotNull
    @Schema(example = "754159wf")
    private String mp;

    @NotNull
    @JsonIgnore
    private UserDTO user;

    @NotNull
    private List<SpecialtyDTO> specialties;

    @NotNull
    @JsonIgnore
    private List<ShiftDTO> shifts;
}
