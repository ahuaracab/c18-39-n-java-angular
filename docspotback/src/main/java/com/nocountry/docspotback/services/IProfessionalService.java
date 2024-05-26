package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.models.Specialty;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IProfessionalService extends ICRUDService<Professional, UUID>{
    Professional saveTransactional(Professional professional, List<Specialty> specialties);
    List<Professional> getAllProfessionalsBySpecialityName(@Param("nameSpecialty") String nameSpecialty, int numPage, int pageSize, String orderBy, String sort);

}
