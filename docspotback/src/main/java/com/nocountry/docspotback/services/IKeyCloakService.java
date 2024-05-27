package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.User;

public interface IKeyCloakService {

    boolean addUser(User user) throws Exception;

}
