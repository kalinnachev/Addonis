package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


}
