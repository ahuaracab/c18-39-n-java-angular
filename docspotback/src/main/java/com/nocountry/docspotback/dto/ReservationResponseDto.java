package com.nocountry.docspotback.dto;

import java.time.Instant;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    @NotNull
    private UUID id;

    @NotNull
    private String queryIntent;

    @NotNull
    private Instant appointmentDate;

    @NotNull
    private String patientName;

    @NotNull
    private UUID patientId;

    @NotNull
    private String patientImage;

    @NotNull
    private String patientPhone;
}
