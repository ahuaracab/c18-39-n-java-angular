package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IUserRepo;
import com.nocountry.docspotback.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl extends CRUDImpl<User, UUID> implements IUserService {

    @Autowired
    private IUserRepo repo;

    @Override
    protected IGenericRepo<User, UUID> getRepo() {
        return repo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
