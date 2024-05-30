package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Reservation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReservationRepo extends IGenericRepo<Reservation, UUID> {

    @Query(value = "SELECT r.id_reservation, r.appointment_date, r.query_intent, p.id_patient, p.name_patient, p.photo_patient, p.cellphone_patient\n" +
            "FROM reservations r\n" +
            "inner join patients p on r.id_patient = p.id_patient\n" +
            "inner join shifts s on r.id_shift = s.id_shift\n" +
            "where s.professional_id_professional = :professionalId", nativeQuery = true)
    List<Object[]> findByProfessional(@Param("professionalId") UUID professionalId);
}
