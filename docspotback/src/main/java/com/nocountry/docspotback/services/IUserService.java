package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

public interface IUserService extends ICRUDService<User, UUID>{
    Optional<User> findByEmail(String email);
    Patient getPatientByUserId(@Param("userId")UUID userId);
    Professional getProfessionalByUserId(@Param("id")UUID id);

}
