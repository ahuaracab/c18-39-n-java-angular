package com.nocountry.docspotback.dto;


import com.nocountry.docspotback.models.Reservation;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalTime hoursTime;
    
    @NotNull
    private Boolean stateShift;
    
    @NotNull
    private Boolean repeatShift;
    
    @NotNull
    private UUID idProfessional;
    
    private Reservation reservations;
}
