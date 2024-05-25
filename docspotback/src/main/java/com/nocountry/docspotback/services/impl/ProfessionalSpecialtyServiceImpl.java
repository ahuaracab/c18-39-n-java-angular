package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IProfessionalSpecialtyRepo;
import com.nocountry.docspotback.services.IProfessionalSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<ProfessionalSpecialty> getAllProfessionalsBySpecialityName(String nameSpecialty, Pageable pageable) {
        if (nameSpecialty == null || nameSpecialty.isEmpty()) {
            throw new IllegalArgumentException("Specialty name must not be null or empty");
        }
        if (pageable == null) {
            throw new NullPointerException("Pageable must not be null");
        }
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            throw new IllegalArgumentException("Page and size must be non-negative");
        }
        if (repo == null) {
            throw new NullPointerException("Repository must not be null");
        }

        return repo.getAllProfessionalsBySpecialityName(nameSpecialty, pageable);
    }

}
