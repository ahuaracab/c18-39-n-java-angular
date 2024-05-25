package com.nocountry.docspotback.services;

import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.models.Reservation;

import java.util.UUID;
import java.util.List;

public interface IReservationService extends ICRUDService<Reservation, UUID> {
    List<ReservationResponseDto> findReservationsByProfessionalId(UUID professionalId);
}
