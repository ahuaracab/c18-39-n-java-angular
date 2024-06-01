package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepo extends IGenericRepo<User, UUID> {
    @Query(value = "SELECT * FROM users u WHERE u.email=:email",nativeQuery = true)
    Optional<User> findByEmail(@Param("email")String email);
}
