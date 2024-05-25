package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Reservation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReservationRepo extends IGenericRepo<Reservation, UUID> {

    @Query(value = "SELECT r.id, r.appointment_date, r.query_intent, p.id as patien_id , p.name_patient, p.photo_patient, p.cellphone_patient\n" +
            "FROM reservations r\n" +
            "inner join patients p on r.patient_id = p.id\n" +
            "inner join shifts s on r.shift_id = s.id\n" +
            "where s.professional_id_professional = :professionalId", nativeQuery = true)
    List<Object[]> findByProfessional(@Param("professionalId") UUID professionalId);
}
