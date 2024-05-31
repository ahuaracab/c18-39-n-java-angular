package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.User;
import jakarta.transaction.Transactional;

import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILoginRepo extends IGenericRepo<User, UUID>{

    @Query("FROM User us where us.email =:username")
    User checkUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE User us SET us.password =:password WHERE us.email =:username")
    void changePassword(@Param("password") String password, @Param("username") String username);

}