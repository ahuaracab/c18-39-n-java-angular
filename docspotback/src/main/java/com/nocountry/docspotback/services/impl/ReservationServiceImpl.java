package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.models.ReservationList;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IReservationListRepo;
import com.nocountry.docspotback.repositories.IReservationRepo;
import com.nocountry.docspotback.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl extends CRUDImpl<Reservation, UUID> implements IReservationService {

    @Autowired
    private IReservationRepo repo;

    @Autowired
    private IReservationListRepo listRepo;
    
    @Override
    protected IGenericRepo<Reservation, UUID> getRepo() {
        return repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDto> findReservationsByProfessionalId(UUID professionalId) {
        List<Object[]> reservations = repo.findByProfessional(professionalId);
         List<ReservationResponseDto> reservationResponse = reservations.stream().map((reservationQueryResp) -> {
           ReservationResponseDto reservationresponse = new ReservationResponseDto();
           reservationresponse.setId((UUID) reservationQueryResp[0]);
           reservationresponse.setAppointmentDate((Instant) reservationQueryResp[1]);
           reservationresponse.setQueryIntent((String) reservationQueryResp[2]);
           reservationresponse.setPatientId((UUID) reservationQueryResp[3]);
           reservationresponse.setPatientName((String) reservationQueryResp[4]);
           reservationresponse.setPatientImage((String) reservationQueryResp[5]);
           reservationresponse.setPatientPhone((String) reservationQueryResp[6]);
 
           return reservationresponse;

        }).collect(Collectors.toList());

        return reservationResponse;
    }

	@Override
	public List<ReservationList> findAllReservationListsByPatientId(UUID idPatient) {
		// TODO Auto-generated method stub
		return listRepo.findAllReservationListsByPatientId(idPatient);
	}

	@Override
	public List<ReservationList> findAllReservationListsByProfesionalId(UUID idProfessional) {
		// TODO Auto-generated method stub
		return listRepo.findAllReservationListsByProfesionalId(idProfessional);
	}
	
	@Transactional
	@Override
	public Reservation saveTransaction(Reservation reservation) {
		listRepo.updateStateShift(reservation.getShift().getIdShift());
		return repo.save(reservation);
	}
}
