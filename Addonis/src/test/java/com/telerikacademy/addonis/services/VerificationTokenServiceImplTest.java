package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.Helpers;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telerikacademy.addonis.Helpers.createMockUser;
import static com.telerikacademy.addonis.Helpers.createMockVerificationToken;

@ExtendWith(MockitoExtension.class)
public class VerificationTokenServiceImplTest {

    @Mock
    VerificationTokenRepository repository;

    @InjectMocks
    VerificationTokenServiceImpl service;

    //TODO how??????????
//    @Test
//    public void createVerificationToken_should_callRepository() {
//        VerificationToken mockToken = createMockVerificationToken();
//
//        service.createVerificationToken(mockToken.getUser(), mockToken.getToken());
//
//        Mockito.verify(repository, Mockito.times(1))
//                .create(mockToken);
//    }
}
