package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.Helpers;
import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.repositories.contracts.TargetIdeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telerikacademy.addonis.Helpers.createMockTargetIde;

@ExtendWith(MockitoExtension.class)
public class TargetIdeServiceImplTest {

    @Mock
    TargetIdeRepository repository;

    @InjectMocks
    TargetIdeServiceImpl service;

    @Test
    public void getByName_should_callRepository() {
        TargetIde mockTargetIde = createMockTargetIde();

        service.getByName(mockTargetIde.getName());

        Mockito.verify(repository, Mockito.times(1))
                .getByName(mockTargetIde.getName());
    }

    @Test
    public void getById_should_callRepository() {
        TargetIde mockTargetIde = createMockTargetIde();

        service.getById(mockTargetIde.getId());

        Mockito.verify(repository, Mockito.times(1))
                .getById(mockTargetIde.getId());
    }

    @Test
    public void getAll_should_callRepository() {
        service.getAll();

        Mockito.verify(repository, Mockito.times(1))
                .getAll();
    }
}
