package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Reservation;

import java.util.UUID;
import java.util.List;

public interface IReservationService extends ICRUDService<Reservation, UUID> {
    List<Reservation> findReservationsByProfessionalId(UUID professionalId);
}
