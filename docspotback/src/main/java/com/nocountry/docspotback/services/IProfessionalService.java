package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.models.Specialty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IProfessionalService extends ICRUDService<Professional, UUID>{
    Professional saveTransactional(Professional professional, List<Specialty> specialties);
    Page<Professional> getAllProfessionalsBySpecialityName(@Param("nameSpecialty") String nameSpecialty, Pageable pageable);

    Page<Professional>findAllProfessional(Pageable pageable);

}
