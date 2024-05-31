package com.nocountry.docspotback.services;


import com.nocountry.docspotback.models.ResetToken;

public interface IResetTokenService {

    ResetToken findByToken(String token);

    void save(ResetToken token);

    void delete(ResetToken token);

}

