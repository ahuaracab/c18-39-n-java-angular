package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Professional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IProfessionalRepo extends IGenericRepo<Professional, UUID>{
  /*  @Query(value = "Select * FROM professionals as p" +
            "INNER JOIN Specialty as s" +
            "WHERE p.",nativeQuery = true)
    List<Object[]>findAllProfesionalBySpecialty(String nameSpecialty);*/
}
