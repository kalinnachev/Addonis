package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void deleteVerificationToken(User user) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.
                getAll().stream().filter(u-> u.getUser().getPhoneNumber().equals(user.getPhoneNumber())).findFirst();
        verificationTokenRepository.delete(verificationToken.get().getId());
    }

    @Override
    public void verifyToken(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.
                getAll().stream().filter(t-> t.getToken().equals(token)).findFirst();
        if(verificationToken.isEmpty()){
            throw new EntityNotFoundException("Token","value",token);
        }
        verificationToken.get().getUser().setEnabled(true);
    }
}
