package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.repositories.contracts.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telerikacademy.addonis.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    RatingRepository repository;

    @InjectMocks
    RatingServiceImpl service;

    @Test
    public void create_should_throw_when_ratingExists() {
        Addon mockAddon = createMockAddon();
        Rating mockRating = createMockRating(mockAddon);

        Mockito.when(repository.getByUserAndAddon(mockAddon, mockRating.getUser()))
                .thenReturn(mockRating);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.create(mockRating));
    }

    @Test
    public void create_should_callRepository_when_ratingDoesNotExist() {
        Addon mockAddon = createMockAddon();
        Rating mockRating = createMockRating(mockAddon);

        Mockito.when(repository.getByUserAndAddon(mockAddon, mockRating.getUser()))
                .thenThrow(EntityNotFoundException.class);
        service.create(mockRating);

        Mockito.verify(repository, Mockito.times(1))
                .create(mockRating);
    }


}
