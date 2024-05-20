package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IReservationRepo;
import com.nocountry.docspotback.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationServiceImpl extends CRUDImpl<Reservation, UUID> implements IReservationService {

    @Autowired
    private IReservationRepo repo;

    @Override
    protected IGenericRepo<Reservation, UUID> getRepo() {
        return repo;
    }
}
