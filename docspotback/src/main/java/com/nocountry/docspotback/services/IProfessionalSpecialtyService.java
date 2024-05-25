package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.ProfessionalSpecialty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IProfessionalSpecialtyService extends ICRUDService<ProfessionalSpecialty, UUID> {

    List<ProfessionalSpecialty> getSpecialtyByProfessionalId(@Param("idProfessional") UUID idProfessional);

    List<ProfessionalSpecialty> getAllProfessionalsBySpecialityName(@Param("nameSpecialty") String nameSpecialty, Pageable pageable);

}
