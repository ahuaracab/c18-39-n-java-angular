package com.nocountry.docspotback.dto;

import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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
    private UserDTO user;

    @NotNull
    private List<SpecialtyDTO> specialties;

    @NotNull
    private List<ShiftDTO> shifts;
}
