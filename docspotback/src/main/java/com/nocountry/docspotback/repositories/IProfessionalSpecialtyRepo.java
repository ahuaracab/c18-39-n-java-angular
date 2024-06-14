package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.ProfessionalSpecialty;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IProfessionalSpecialtyRepo extends IGenericRepo<ProfessionalSpecialty, UUID>{

	//@Transactional
    @Modifying
    @Query(value = "INSERT INTO professional_specialty(id_professional,id_specialty) VALUES (:idProfessional,:idSpecialty)",nativeQuery = true)
    void saveSpecialty(@Param("idProfessional") UUID idProfessional, @Param("idSpecialty") UUID idSpecialty);

    @Query("FROM ProfessionalSpecialty ps WHERE ps.professional.idProfessional = :idProfessional")
    List<ProfessionalSpecialty> getSpecialtyByProfessionalId(@Param("idProfessional") UUID idProfessional);


}
