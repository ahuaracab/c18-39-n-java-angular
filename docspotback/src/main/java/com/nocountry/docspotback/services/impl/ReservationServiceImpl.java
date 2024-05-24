package com.nocountry.docspotback.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IReservationRepo;
import com.nocountry.docspotback.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl extends CRUDImpl<Reservation, UUID> implements IReservationService {

    @Autowired
    private IReservationRepo repo;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected IGenericRepo<Reservation, UUID> getRepo() {
        return repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByProfessionalId(UUID professionalId) {
        List<Reservation> reservations = repo.findByProfessional(professionalId);

        try {
            String reservationJson = objectMapper.writeValueAsString(reservations.get(0));
            System.out.println("RESERVATION: " + reservationJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}
