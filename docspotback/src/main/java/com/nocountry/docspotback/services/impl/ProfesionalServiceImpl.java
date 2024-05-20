package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IProfessionalRepo;
import com.nocountry.docspotback.services.IProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfesionalServiceImpl extends CRUDImpl<Professional, UUID> implements IProfessionalService {

    @Autowired
    private IProfessionalRepo repo;

    @Override
    protected IGenericRepo<Professional, UUID> getRepo() {
        return repo;
    }
}
