package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;

public interface VerificationTokenService {
    void createVerificationToken(User user, String token);
}
