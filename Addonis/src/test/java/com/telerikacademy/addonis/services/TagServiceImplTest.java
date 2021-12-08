package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.repositories.contracts.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    @Mock
    TagRepository repository;

    @InjectMocks
    TagServiceImpl service;

    @Test
    public void getAll_should_callRepository() {
        service.getAll();

        Mockito.verify(repository, Mockito.times(1))
                .getAll();
    }
}
