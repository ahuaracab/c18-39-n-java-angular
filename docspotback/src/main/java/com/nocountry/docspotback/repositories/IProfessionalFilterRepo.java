package com.nocountry.docspotback.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nocountry.docspotback.models.ProfessionalFilter;

public interface IProfessionalFilterRepo extends IGenericRepo<ProfessionalFilter, UUID>{
	
    @Query(value = "SELECT pf.id_professional, pf.mp, pf.name_professional, pf.reputation, pf.value_query, pf.url_photo, pf.id_specialty, pf.name_specialty\r\n"
    		+ "	FROM professional_filter pf WHERE pf.id_specialty=:idSpecialty",nativeQuery = true)
    Page<ProfessionalFilter>getAllProfessionalsByIdSpecialty(@Param("idSpecialty") UUID idSpecialty, Pageable pageable);

}
