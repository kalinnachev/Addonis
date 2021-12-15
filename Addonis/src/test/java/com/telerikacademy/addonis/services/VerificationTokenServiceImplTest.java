package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.Helpers;
import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telerikacademy.addonis.Helpers.createMockUser;
import static com.telerikacademy.addonis.Helpers.createMockVerificationToken;

@ExtendWith(MockitoExtension.class)
public class VerificationTokenServiceImplTest {

    @Mock
    VerificationTokenRepository repository;

    @InjectMocks
    VerificationTokenServiceImpl service;

    @Captor
    ArgumentCaptor<VerificationToken> verificationTokenArgumentCaptor;

    @Test
    public void createVerificationToken_should_callRepository() {
        VerificationToken mockToken = createMockVerificationToken();

        service.createVerificationToken(mockToken.getUser(), mockToken.getToken());

        Mockito.verify(repository).create(verificationTokenArgumentCaptor.capture());
        VerificationToken verificationToken = verificationTokenArgumentCaptor.getValue();

        //Verify
        Mockito.verify(repository, Mockito.times(1))
                .create(verificationToken);
    }

    @Test
    public void deleteVerificationToken_should_callRepository() {
        User mockUser = createMockUser();
        List<VerificationToken> verificationTokens = new ArrayList<>();
        verificationTokens.add(createMockVerificationToken());

        Mockito.when(repository.
                getAll()).thenReturn(verificationTokens);
        service.deleteVerificationToken(mockUser);
        Mockito.verify(repository).delete(verificationTokens.get(0).getId());

        //Verify
        Mockito.verify(repository, Mockito.times(1))
                .delete(verificationTokens.get(0).getId());
    }

    @Test
    public void findUserByToken_should_callRepository(){
        String mockToken = "token";

        Mockito.when(repository.findByTokenValue(mockToken))
                .thenReturn(createMockVerificationToken());

        service.findUserByToken(mockToken);
        Mockito.verify(repository,Mockito.times(1))
                .findByTokenValue(mockToken);

    }
}
