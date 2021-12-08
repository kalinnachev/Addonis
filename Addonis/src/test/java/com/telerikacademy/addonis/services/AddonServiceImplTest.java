package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.Helpers;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.telerikacademy.addonis.Helpers.createMockAddon;

@ExtendWith(MockitoExtension.class)
public class AddonServiceImplTest {

    @Mock
    AddonRepository addonRepository;

    @Mock
    RepoInfoService repoInfoService;

    @Mock
    FileService fileService;

    @InjectMocks
    AddonServiceImpl addonService;

    @Test
    public void getById_should_callRepository() {
        Addon mockAddon = createMockAddon();

        addonService.getById(mockAddon.getId());

        Mockito.verify(addonRepository, Mockito.times(1))
                .getById(mockAddon.getId());
    }

    @Test
    public void getById_should_returnAddon_when_matchExists() {
        Addon mockAddon = createMockAddon();

        Mockito.when(addonRepository.getById(mockAddon.getId()))
                .thenReturn(mockAddon);

        Addon result = addonService.getById(mockAddon.getId());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mockAddon.getId(), result.getId()),
                () -> Assertions.assertEquals(mockAddon.getName(), result.getName()),
                () -> Assertions.assertEquals(mockAddon.getTargetIde(), result.getTargetIde()),
                () -> Assertions.assertEquals(mockAddon.getCreator(), result.getCreator()),
                () -> Assertions.assertEquals(mockAddon.getDescription(), result.getDescription()),
                () -> Assertions.assertEquals(mockAddon.getOriginUrl(), result.getOriginUrl()),
                () -> Assertions.assertEquals(mockAddon.getBinaryContentUrl(), result.getBinaryContentUrl()),
                () -> Assertions.assertEquals(mockAddon.getNumberOfDownloads(), result.getNumberOfDownloads()),
                () -> Assertions.assertEquals(mockAddon.getCreationDate(), result.getCreationDate()),
                () -> Assertions.assertEquals(mockAddon.isApproved(), result.isApproved()),
                () -> Assertions.assertEquals(mockAddon.getRepoInfo(), result.getRepoInfo()),
                () -> Assertions.assertEquals(mockAddon.getTags(), result.getTags())
        );
    }

    @Test
    public void getAll_should_callRepository(){
        Mockito.when(addonRepository.getAll())
                .thenReturn(new ArrayList<>());

        addonService.getAll();

        Mockito.verify(addonRepository, Mockito.times(1)).getAll();
    }
}
