package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IPatientRepo;
import com.nocountry.docspotback.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl extends CRUDImpl<Patient, UUID> implements IPatientService {

    @Autowired
    private IPatientRepo repo;

    @Override
    protected IGenericRepo<Patient, UUID> getRepo() {
        return repo;
    }

    @Override
    public List<Reservation> getAllReservationByPatientId(UUID id) {
        return repo.getAllReservationByPatientId(id);
    }
}
