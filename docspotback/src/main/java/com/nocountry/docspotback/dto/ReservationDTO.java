package com.nocountry.docspotback.dto;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private UUID idReservation;

    private String queryIntent;

    @NotNull
    private PatientDTO patient;

    @NotNull
    private ShiftDTO shift;
}
