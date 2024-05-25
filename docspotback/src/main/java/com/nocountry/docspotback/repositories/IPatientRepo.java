package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/*
{
}
*/

public interface IPatientRepo extends IGenericRepo<Patient, UUID>  {

    @Query(value = "SELECT * FROM reservations as r WHERE r.id_patient=:id",nativeQuery = true)
    List<Reservation>getAllReservationByPatientId(@Param("id") UUID id);
}