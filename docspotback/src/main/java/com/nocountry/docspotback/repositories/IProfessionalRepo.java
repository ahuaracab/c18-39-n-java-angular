package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IProfessionalRepo extends IGenericRepo<Professional, UUID>{


    @Query(value = "SELECT pr.id_professional,pr.url_photo, pr.name_professional,pr.value_query,pr.reputation,pr.mp,pr.created_at,pr.updated_at,pr.deleted_at,pr.is_deleted,pr.user_id_user FROM professionals pr INNER JOIN users u ON u.id_user=pr.user_id_user WHERE pr.user_id_user=:id",nativeQuery = true)
    Professional getProfessionalByUserId(@Param("id")UUID id);
    
    @Query(value = "SELECT p FROM Professional p")
    Page<Professional>findAllProfessional(Pageable pageable);
    

}
