package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IPatientService extends ICRUDService<Patient, UUID>{
    List<Reservation> getAllReservationByPatientId(UUID id);
}
