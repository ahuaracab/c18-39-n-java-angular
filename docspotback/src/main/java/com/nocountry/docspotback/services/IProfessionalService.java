package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalFilter;
import com.nocountry.docspotback.models.ProfessionalView;
import com.nocountry.docspotback.models.Specialty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProfessionalService extends ICRUDService<Professional, UUID>{
    Professional saveTransactional(Professional professional, List<Specialty> specialties);
    
    Page<ProfessionalFilter>getAllProfessionalsByIdSpecialty(UUID idSpecialty, Pageable pageable);

    Page<Professional>findAllProfessional(Pageable pageable);

    List<ProfessionalView>findAllProfessionalView();
    
    public List<ProfessionalFilter>getAllProfByIdSpecialty(UUID idSpecialty);
}
