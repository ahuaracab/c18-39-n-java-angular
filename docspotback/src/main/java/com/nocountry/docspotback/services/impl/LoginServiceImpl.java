package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.repositories.ILoginRepo;
import com.nocountry.docspotback.services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {


    @Autowired
    private ILoginRepo repo;

    @Override
    public User checkUsername(String username) {
        return repo.checkUsername(username);
    }

    @Override
    public void changePassword(String password, String username) {
        //repo.changePassword(bcrypt.encode(password), username);
    }
}
