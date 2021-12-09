package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.repositories.contracts.GitHubRestApiConsumer;
import com.telerikacademy.addonis.repositories.contracts.RepoInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.telerikacademy.addonis.Helpers.createMockAddon;

/*
https://www.baeldung.com/mockito-argumentcaptor
 */

@ExtendWith(MockitoExtension.class)
class RepoInfoServiceImplTest {

    @Mock
    RepoInfoRepository repository;

    @Mock
    GitHubRestApiConsumer restApiConsumer;

    @Mock
    AddonRepository addonRepository;

    @InjectMocks
    RepoInfoServiceImpl service;

    @Captor
    ArgumentCaptor<RepoInfo> repoInfoArg;

    @Test
    public void createInfoForAddon_should_create_repoInfo_and_call_ApiConsumer(){
        // Arrange
        Addon mockAddon = createMockAddon();

        // Act
        service.createInfoForAddon(mockAddon);
        Mockito.verify(repository).create(repoInfoArg.capture());
        RepoInfo repoInfo = repoInfoArg.getValue();

        //Verify
        Mockito.verify(restApiConsumer, Mockito.times(1))
                        .populateRepoInfoFromApi(mockAddon.getOriginUrl(), repoInfo);
        Mockito.verify(repository, Mockito.times(1))
                .create(repoInfo);
    }

    @Test
    public void updateInfoForAddon_should_call_ApiConsumer(){
        // Arrange
        Addon mockAddon = createMockAddon();
        RepoInfo repoInfo = mockAddon.getRepoInfo();

        // Act
        service.updateInfoForAddon(mockAddon);

        // Assert
        Mockito.verify(restApiConsumer, Mockito.times(1))
                .populateRepoInfoFromApi(mockAddon.getOriginUrl(), repoInfo);

    }

    @Test
    public void scheduledUpdate_should_call_ApiConsumer_and_Repository(){
        // Arrange
        Addon mockAddon = createMockAddon();
        RepoInfo repoInfo = mockAddon.getRepoInfo();
        Mockito.when(addonRepository.getAll())
                .thenReturn(List.of(mockAddon, mockAddon));

        // Act
        service.scheduledUpdate();

        // Assert
        Mockito.verify(restApiConsumer, Mockito.times(2))
                .populateRepoInfoFromApi(mockAddon.getOriginUrl(), repoInfo);
        Mockito.verify(repository, Mockito.times(2))
                .update( repoInfo);
    }
}