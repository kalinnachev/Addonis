package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.create(verificationToken);
    }

    @Override
    public void deleteVerificationToken(User user) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.
                getAll().stream().filter(u-> u.getUser().getPhoneNumber().equals(user.getPhoneNumber())).findFirst();

        if(verificationToken.isPresent())
            verificationTokenRepository.delete(verificationToken.get().getId());
    }

    @Override
    public User findUserByToken(String token) {
       return verificationTokenRepository.findByTokenValue(token).getUser();
    }
}
