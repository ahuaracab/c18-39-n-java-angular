package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IProfessionalSpecialtyRepo;
import com.nocountry.docspotback.services.IProfessionalSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessionalSpecialtyServiceImpl extends CRUDImpl<ProfessionalSpecialty, UUID> implements IProfessionalSpecialtyService {

    @Autowired
    private IProfessionalSpecialtyRepo repo;

    @Override
    protected IGenericRepo<ProfessionalSpecialty, UUID> getRepo() {
        return repo;
    }


    @Override
    public List<ProfessionalSpecialty> getSpecialtyByProfessionalId(UUID idProfessional) {
        return repo.getSpecialtyByProfessionalId(idProfessional);
    }
}
