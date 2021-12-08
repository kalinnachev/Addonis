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


}
