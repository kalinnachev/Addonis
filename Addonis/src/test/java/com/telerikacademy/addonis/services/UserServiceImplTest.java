package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.exceptions.UnauthorizedFailureException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import static com.telerikacademy.addonis.Helpers.createMockRole;
import static com.telerikacademy.addonis.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AddonService addonService;

    @Mock
    FileService fileService;

    @InjectMocks
    UserServiceImpl service;

    @Test
    void getAll_should_callRepository() {
        Mockito.when(userRepository.getAll())
                .thenReturn(new ArrayList<>());

        service.getAll();

        Mockito.verify(userRepository, Mockito.times(1)).getAll();
    }

    @Test
    void getById_should_returnUser_when_matchExist() {
        User mockUser = createMockUser();
        Mockito.when(userRepository.getById(mockUser.getId())).thenReturn(mockUser);

        User result = service.getById(mockUser.getId());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mockUser.getId(), result.getId()),
                () -> Assertions.assertEquals(mockUser.getUsername(), result.getUsername()),
                () -> Assertions.assertEquals(mockUser.getPassword(), result.getPassword()),
                () -> Assertions.assertEquals(mockUser.getFirstName(), result.getFirstName()),
                () -> Assertions.assertEquals(mockUser.getLastName(), result.getLastName()),
                () -> Assertions.assertEquals(mockUser.getEmail(), result.getEmail()),
                () -> Assertions.assertEquals(mockUser.getPhoneNumber(), result.getPhoneNumber()),
                () -> Assertions.assertEquals(mockUser.getPictureUrl(), result.getPictureUrl()),
                () -> Assertions.assertEquals(mockUser.isBlocked(), result.isBlocked()),
                () -> Assertions.assertEquals(mockUser.getRole(), result.getRole())
        );
    }

    @Test
    void getByUsername_should_returnUser_when_matchExist() {
        User mockUser = createMockUser();
        Mockito.when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(mockUser);

        User result = service.getByUsername(mockUser.getUsername());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mockUser.getId(), result.getId()),
                () -> Assertions.assertEquals(mockUser.getUsername(), result.getUsername()),
                () -> Assertions.assertEquals(mockUser.getPassword(), result.getPassword()),
                () -> Assertions.assertEquals(mockUser.getFirstName(), result.getFirstName()),
                () -> Assertions.assertEquals(mockUser.getLastName(), result.getLastName()),
                () -> Assertions.assertEquals(mockUser.getEmail(), result.getEmail()),
                () -> Assertions.assertEquals(mockUser.getPhoneNumber(), result.getPhoneNumber()),
                () -> Assertions.assertEquals(mockUser.getPictureUrl(), result.getPictureUrl()),
                () -> Assertions.assertEquals(mockUser.isBlocked(), result.isBlocked()),
                () -> Assertions.assertEquals(mockUser.getRole(), result.getRole())
        );
    }

    @Test
    public void create_should_throw_when_userWithSameUsernameExists() {
        User mockUser = createMockUser();
        User mockUserCreate = createMockUser();
        mockUserCreate.setId(2);
        File file = new File("dummyFile");

        Mockito.when(userRepository.findByUsername(mockUser.getUsername()))
                .thenReturn(mockUser);

        Assertions.assertThrows(DuplicateEntityException.class, () -> service.create(mockUserCreate, file));
    }

    @Test
    public void create_should_throw_when_userWithSameEmailExists() {
        User mockUser = createMockUser();
        User mockUserCreate = createMockUser();
        mockUserCreate.setId(2);
        File file = new File("dummyFile");

        Mockito.when(userRepository.findByUsername(mockUserCreate.getUsername()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByEmail(mockUserCreate.getEmail()))
                .thenReturn(mockUser);

        Assertions.assertThrows(DuplicateEntityException.class, () -> service.create(mockUserCreate, file));
    }

    @Test
    public void create_should_throw_when_userWithSamePhoneExists() {
        User mockUser = createMockUser();
        User mockUserCreate = createMockUser();
        mockUserCreate.setId(2);
        File file = new File("dummyFile");

        Mockito.when(userRepository.findByUsername(mockUserCreate.getUsername()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByEmail(mockUserCreate.getEmail()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByTelephone(mockUserCreate.getPhoneNumber()))
                .thenReturn(mockUser);

        Assertions.assertThrows(DuplicateEntityException.class, () -> service.create(mockUserCreate, file));
    }

    @Test
    public void create_should_callRepository_when_userIsVerified() {
        User mockUser = createMockUser();
        File file = new File("dummyFile");

        Mockito.when(userRepository.findByUsername(mockUser.getUsername()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByEmail(mockUser.getEmail()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByTelephone(mockUser.getPhoneNumber()))
                .thenThrow(EntityNotFoundException.class);

        service.create(mockUser,file);

        Mockito.verify(userRepository, Mockito.times(1))
                .create(mockUser);
    }

    @Test
    public void update_should_throw_when_userWithSameEmailExists() {
        User mockUser = createMockUser();
        User mockUserUpdate = createMockUser();
        mockUserUpdate.setId(2);
        Optional<File> profilePicture = Optional.empty();

        Mockito.when(userRepository.findByEmail(mockUserUpdate.getEmail()))
                .thenReturn(mockUser);

        Assertions.assertThrows(DuplicateEntityException.class, () -> {
            service.update(mockUserUpdate, profilePicture);
        });
    }


    @Test
    public void update_should_throw_when_userWithSamePhoneExists() {
        User mockUser = createMockUser();
        User mockUserUpdate = createMockUser();
        mockUserUpdate.setId(2);
        Optional<File> profilePicture = Optional.empty();

        Mockito.when(userRepository.findByEmail(mockUserUpdate.getEmail()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByTelephone(mockUserUpdate.getPhoneNumber()))
                .thenReturn(mockUser);

        Assertions.assertThrows(DuplicateEntityException.class, () -> {
            service.update(mockUserUpdate, profilePicture);
        });
    }

    @Test
    public void update_should_callRepository_when_userIsVerified() {
        User mockUser = createMockUser();
        Optional<File> profilePicture = Optional.empty();

        Mockito.when(userRepository.findByEmail(mockUser.getEmail()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(userRepository.findByTelephone(mockUser.getPhoneNumber()))
                .thenThrow(EntityNotFoundException.class);

        service.update(mockUser, profilePicture);

        Mockito.verify(userRepository, Mockito.times(1))
                .update(mockUser);
    }

    @Test
    public void block_should_throw_when_userIsNotAdmin() {
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));

        Assertions.assertThrows(UnauthorizedFailureException.class, () -> {
            service.block(2, mockUser);
        });
    }

    @Test
    public void block_should_blockUser_when_userIsAdmin() {
        User mockAdmin = createMockUser();
        User mockUser = createMockUser();
        mockUser.setId(2);
        mockUser.setRole(createMockRole("User"));

        Mockito.when(userRepository.getById(2))
                        .thenReturn(mockAdmin);

        Assertions.assertTrue(service.block(2, mockAdmin).isBlocked());
    }

    @Test
    public void unblock_should_throw_when_userIsNotAdmin() {
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));

        Assertions.assertThrows(UnauthorizedFailureException.class, () -> {
            service.unblock(2, mockUser);
        });
    }

    @Test
    public void unblock_should_unblockUser_when_userIsAdmin() {
        User mockAdmin = createMockUser();
        User mockUser = createMockUser();
        mockUser.setId(2);
        mockUser.setRole(createMockRole("User"));

        Mockito.when(userRepository.getById(2))
                .thenReturn(mockAdmin);

        Assertions.assertTrue(service.block(2, mockAdmin).isBlocked());
    }

    @Test
    public void unblock_should_callRepository_when_userIsAdmin() {
        User mockAdmin = createMockUser();
        User mockUser = createMockUser();
        mockUser.setId(2);
        mockUser.setRole(createMockRole("User"));

        Mockito.when(userRepository.getById(2))
                .thenReturn(mockUser);
        service.unblock(2,mockAdmin);


        Mockito.verify(userRepository, Mockito.times(1))
                .update(mockUser);
    }

    @Test
    public void delete_should_throw_when_userIsNotAdmin() {
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));

        Assertions.assertThrows(UnauthorizedFailureException.class, () -> {
            service.delete(2, mockUser);
        });
    }

    @Test
    public void delete_should_callRepository_when_userIsAdmin() {
        User mockAdmin = createMockUser();
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));
        mockUser.setId(2);

        Mockito.when(addonService.getByUser(mockUser.getId(), Optional.of(mockAdmin)))
                        .thenReturn(new ArrayList<>());
        service.delete(mockUser.getId(), mockAdmin);

        Mockito.verify(userRepository, Mockito.times(1))
                .delete(mockUser.getId());
    }

    @Test
    public void search_should_throw_when_userIsNotAdmin() {
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));
        Optional<String> username = Optional.empty();
        Optional<String> email = Optional.empty();
        Optional<String> phoneNumber = Optional.empty();

        Assertions.assertThrows(UnauthorizedFailureException.class, () -> {
            service.search(username, email, phoneNumber,mockUser);
        });
    }

    @Test
    public void search_should_callRepository_when_userIsAdmin() {
        User mockAdmin = createMockUser();
        Optional<String> username = Optional.empty();
        Optional<String> email = Optional.empty();
        Optional<String> phoneNumber = Optional.empty();

        service.search(username,email,phoneNumber,mockAdmin);

        Mockito.verify(userRepository, Mockito.times(1))
                .getAll();
    }

    @Test
    public void search_should_callRepository_when_userIsAdminAndOptionalIsNotEmpty() {
        User mockAdmin = createMockUser();
        Optional<String> username = Optional.of("mockUsername");
        Optional<String> email = Optional.empty();
        Optional<String> phoneNumber = Optional.empty();

        service.search(username,email,phoneNumber,mockAdmin);

        Mockito.verify(userRepository, Mockito.times(1))
                .search(username,email,phoneNumber);
    }
}
