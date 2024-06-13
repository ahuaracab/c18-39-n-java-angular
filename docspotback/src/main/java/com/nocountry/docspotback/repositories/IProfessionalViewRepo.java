package com.nocountry.docspotback.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.nocountry.docspotback.models.ProfessionalView;

public interface IProfessionalViewRepo extends IGenericRepo<ProfessionalView, UUID>{

    @Query(value = "SELECT p.id_professional, p.url_photo, p.mp, p.name_professional, p.value_query, p.reputation, p.name_specialty FROM professional_view p",nativeQuery=true)
    List<ProfessionalView>findAllProfessionalView();
}
