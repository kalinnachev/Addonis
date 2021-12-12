package com.telerikacademy.addonis.repositories.contracts;


import com.telerikacademy.addonis.models.VerificationToken;

public interface VerificationTokenRepository extends CRUDRepository<VerificationToken>{

    VerificationToken findByTokenValue(String value);

}
