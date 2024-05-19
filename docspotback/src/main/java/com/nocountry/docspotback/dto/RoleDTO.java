package com.nocountry.docspotback.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RoleDTO {
    private UUID idRole;

    @NotNull
    private String nameRole;

    @NotNull
    private String descriptionRole;
}
