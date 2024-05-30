package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IUserRepo extends IGenericRepo<User, UUID> {
    @Query("FROM User us where us.email =:username")
    User findOneByUsername(String username);

}
