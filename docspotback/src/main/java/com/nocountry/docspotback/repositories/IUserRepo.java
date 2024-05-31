package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepo extends IGenericRepo<User, UUID> {
    Optional<User> findByEmail(String email);
}
