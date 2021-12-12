package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;

public interface VerificationTokenService {

    void createVerificationToken(User user, String token);

    void deleteVerificationToken(User user);

    User findUserByToken(String token);
}
