package com.nocountry.docspotback.services;

import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.models.ReservationList;

import java.util.UUID;
import java.util.List;

public interface IReservationService extends ICRUDService<Reservation, UUID> {
    List<ReservationResponseDto> findReservationsByProfessionalId(UUID professionalId);
    
	List<ReservationList> findAllReservationListsByPatientId(UUID idPatient);

	List<ReservationList> findAllReservationListsByProfesionalId(UUID idProfessional);

	public Reservation saveTransaction(Reservation reservation);
}
