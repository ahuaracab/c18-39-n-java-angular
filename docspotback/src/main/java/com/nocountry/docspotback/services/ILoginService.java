package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.User;

public interface ILoginService {

    User checkUsername(String username);
    void changePassword(String password, String username);
}
