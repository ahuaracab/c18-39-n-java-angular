package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.ResetToken;

public interface IResetTokenRepo extends IGenericRepo<ResetToken, Integer>{

    ResetToken findByToken(String token);

}
