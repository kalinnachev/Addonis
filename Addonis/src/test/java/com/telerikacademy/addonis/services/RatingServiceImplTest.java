package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;
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
public class RatingServiceImplTest {

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

    @Test
    public void getById_should_returnRating_when_matchExist() {
        Addon mockAddon = createMockAddon();
        Rating mockRating = createMockRating(mockAddon);

        Mockito.when(repository.getById(mockRating.getId()))
                .thenReturn(mockRating);

        Rating result = service.getById(mockRating.getId());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mockRating.getId(), result.getId()),
                () -> Assertions.assertEquals(mockRating.getUser(), result.getUser()),
                () -> Assertions.assertEquals(mockRating.getAddon(), result.getAddon()),
                () -> Assertions.assertEquals(mockRating.getRating(), result.getRating())
        );
    }

    @Test
    public void getByUserAndAddon_should_throw_when_matchDoesNotExist() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();

        Mockito.when(repository.getByUserAndAddon(mockAddon,mockUser))
                        .thenThrow(EntityNotFoundException.class);


        Assertions.assertThrows(EntityNotFoundException.class,
                () -> service.getByUserAndAddon(mockAddon,mockUser));
    }

    @Test
    public void getByUserAndAddon_should_callRepository() {
        Addon mockAddon = createMockAddon();
        Rating mockRating = createMockRating(mockAddon);

        Mockito.when(repository.getByUserAndAddon(mockAddon, mockRating.getUser()))
                .thenReturn(mockRating);

        service.getByUserAndAddon(mockAddon, mockRating.getUser());

        Mockito.verify(repository, Mockito.times(1))
                .getByUserAndAddon(mockAddon, mockRating.getUser());
    }

    @Test
    public void update_should_callRepository_when_matchExist() {
        Addon mockAddon = createMockAddon();
        Rating mockRating = createMockRating(mockAddon);

        service.update(mockRating);

        Mockito.verify(repository, Mockito.times(1))
                .update(mockRating);
    }
}
