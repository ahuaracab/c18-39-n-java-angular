package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
/*
{
}
*/

public interface IPatientRepo extends IGenericRepo<Patient, UUID>  {

    @Query(value = "SELECT * FROM reservations as r WHERE r.id_patient=:id",nativeQuery = true)
    List<Reservation>getAllReservationByPatientId(@Param("id") UUID id);
    
    @Query(value = "SELECT pa.id_patient,pa.name_patient,pa.cellphone_patient,pa.created_at,pa.updated_at,pa.deleted_at,pa.is_deleted,pa.user_id_user FROM patients pa INNER JOIN users us ON us.id_user=pa.user_id_user WHERE us.id_user=:userId",nativeQuery = true)
    Patient getPatientByUserId(@Param("userId")UUID userId);
}