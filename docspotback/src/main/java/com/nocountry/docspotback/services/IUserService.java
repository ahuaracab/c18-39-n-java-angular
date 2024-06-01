package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService extends ICRUDService<User, UUID>{
    Optional<User> findByEmail(String email);

}
