package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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
public class UserDTO extends AuditableDTO{
    private UUID idUser;

    @NotNull
    @UniqueElements
    @Schema(example = "luiscordova@gmail.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Schema(example = "luis123",requiredMode = RequiredMode.REQUIRED)
    private String password;

    @NotNull
    private Boolean active;

    @NotNull
    private List<RoleDTO> roles;

    @JsonManagedReference
    private PatientDTO patient;

    @JsonManagedReference
    private ProfessionalDTO professional;
    

}
