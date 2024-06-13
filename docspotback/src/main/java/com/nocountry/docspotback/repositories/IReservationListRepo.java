package com.nocountry.docspotback.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nocountry.docspotback.models.ReservationList;

public interface IReservationListRepo extends IGenericRepo<ReservationList, UUID>{
	
	@Query(value = "SELECT r.id_reservation, r.query_intent, r.id_patient, r.name_patient, r.id_shift, r.id_professional, r.name_professional, r.name_specialty, r.date_shift, r.hours_time FROM reservation_list r WHERE r.id_patient=:idPatient",nativeQuery = true)
	List<ReservationList> findAllReservationListsByPatientId(@Param("idPatient") UUID idPatient);
	
	@Query(value = "SELECT r.id_reservation, r.query_intent, r.id_patient, r.name_patient, r.id_shift, r.id_professional, r.name_professional, r.name_specialty, r.date_shift, r.hours_time FROM reservation_list r WHERE r.id_professional=:idProfessional",nativeQuery = true)
	List<ReservationList> findAllReservationListsByProfesionalId(@Param("idProfessional") UUID idProfessional);
	
	@Modifying
	@Query(value = "UPDATE Shift\r\n"
			+ "SET stateShift = false\r\n"
			+ "WHERE idShift =: idShift")
	void updateStateShift(@Param("idShift") UUID idShift);
}
