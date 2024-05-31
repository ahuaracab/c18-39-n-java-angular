package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nocountry.docspotback.models.Patient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID idUser;

    @NotNull
    @UniqueElements
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Boolean active;

    @JsonManagedReference
    @NotNull
    private List<RoleDTO> roles;

    @JsonManagedReference
    private PatientDTO patients;

    @JsonManagedReference
    private ProfessionalDTO professional;
}
