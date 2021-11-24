package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        user.setEnabled(true);
        verificationTokenRepository.create(verificationToken);
    }
}
