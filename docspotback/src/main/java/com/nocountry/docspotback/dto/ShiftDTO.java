package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Reservation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private UUID idShift;

    @NotNull
    private Date dateShift;

    @NotNull
    private Boolean sateShift;

    @NotNull
    private Boolean repeatShift;
    
    @NotNull
    private Boolean stateShift;

    @NotNull
    @JsonIgnore
    private ProfessionalDTO professional;

    @NotNull
    @JsonIgnore
    private ReservationDTO reservation;
}
