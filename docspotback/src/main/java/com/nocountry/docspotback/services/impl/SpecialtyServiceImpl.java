package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.ISpecialtyRepo;
import com.nocountry.docspotback.services.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, UUID> implements ISpecialtyService {

    @Autowired
    private ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, UUID> getRepo() {
        return repo;
    }
}
