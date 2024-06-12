package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Reservation;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private UUID idShift = UUID.randomUUID();

    @NotNull
    private LocalDate dateShift;
    
    @NotNull
    private LocalDateTime hoursTime;
    
    @NotNull
    private Boolean stateShift;
    
    @NotNull
    private Boolean repeatShift;
    
   
    private Reservation reservations;
}
