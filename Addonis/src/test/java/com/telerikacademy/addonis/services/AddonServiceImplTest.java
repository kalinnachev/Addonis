package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.exceptions.UnauthorizedFailureException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import static com.telerikacademy.addonis.Helpers.*;

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
    public void getAll_should_callRepository() {
        Mockito.when(addonRepository.getAll())
                .thenReturn(new ArrayList<>());

        addonService.getAll();

        Mockito.verify(addonRepository, Mockito.times(1)).getAll();
    }

    @Test
    public void create_should_throw_when_userIsBlocked() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);
        File dummyFile = new File("dummyFile");

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.create(mockAddon, mockUser, dummyFile));
    }

    @Test
    public void create_should_throw_when_thereIsAddonWithSameName() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        File dummyFile = new File("dummyFile");

        Mockito.when(addonRepository.getByName(mockAddon.getName()))
                .thenReturn(mockAddon);

        Assertions.assertThrows(DuplicateEntityException.class,
                () -> addonService.create(mockAddon, mockUser, dummyFile));
    }

    @Test
    public void create_should_throw_when_thereIsAddonWithSameOriginUrl() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        File dummyFile = new File("dummyFile");

        Mockito.when(addonRepository.getByName(mockAddon.getName()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(addonRepository.findByOriginUrl(mockAddon.getOriginUrl()))
                .thenReturn(mockAddon);

        Assertions.assertThrows(DuplicateEntityException.class,
                () -> addonService.create(mockAddon, mockUser, dummyFile));
    }

    @Test
    public void create_should_callRepository() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        File dummyFile = new File("dummyFile");

        Mockito.when(addonRepository.getByName(mockAddon.getName()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(addonRepository.findByOriginUrl(mockAddon.getOriginUrl()))
                .thenThrow(EntityNotFoundException.class);
        addonService.create(mockAddon, mockUser, dummyFile);

        Mockito.verify(addonRepository, Mockito.times(1))
                .create(mockAddon);

    }

    @Test
    public void update_should_throw_when_userIsNotCreatorOrAdmin() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));
        mockUser.setId(2);
        Optional<File> mockOptional = Optional.empty();

        Mockito.when(addonRepository.getById(mockAddon.getId()))
                .thenReturn(mockAddon);

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.update(mockAddon, mockUser, mockOptional));
    }

    @Test
    public void update_should_throw_when_userIsBlocked() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);
        Optional<File> mockOptional = Optional.empty();

//        Mockito.when(addonRepository.getById(mockAddon.getId()))
//                .thenReturn(mockAddon);

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.update(mockAddon, mockUser, mockOptional));
    }

    @Test
    public void update_should_callRepository() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        Optional<File> mockOptional = Optional.empty();

        Mockito.when(addonRepository.getById(mockAddon.getId()))
                .thenReturn(mockAddon);
        addonService.update(mockAddon, mockUser, mockOptional);

        Mockito.verify(addonRepository, Mockito.times(1))
                .update(mockAddon);
    }

    @Test
    public void delete_should_throw_when_userIsNotCreatorOrAdmin() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));
        mockUser.setId(2);

        Mockito.when(addonRepository.getById(mockAddon.getId()))
                .thenReturn(mockAddon);

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.delete(mockAddon.getId(), mockUser));
    }

    @Test
    public void delete_should_throw_when_userIsBlocked() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

//        Mockito.when(addonRepository.getById(mockAddon.getId()))
//                .thenReturn(mockAddon);

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.delete(mockAddon.getId(), mockUser));
    }

    @Test
    public void delete_should_callRepository() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();

//        Mockito.when(addonRepository.getById(mockAddon.getId()))
//                .thenReturn(mockAddon);
        addonService.delete(mockAddon.getId(), mockUser);

        Mockito.verify(addonRepository, Mockito.times(1))
                .delete(mockAddon.getId());
    }

    @Test
    public void approve_should_throw_when_userIsNotAdmin() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();
        mockUser.setRole(createMockRole("User"));

        Assertions.assertThrows(UnauthorizedFailureException.class,
                () -> addonService.approve(mockAddon, mockUser));
    }

    @Test
    public void approve_should_approve_when_userIsAdmin() {
        Addon mockAddon = createMockAddon();
        User mockUser = createMockUser();

        addonService.approve(mockAddon, mockUser);

        Assertions.assertTrue(mockAddon.isApproved());
    }

    @Test
    public void getFeatured_should_callRepository() {
        Mockito.when(addonRepository.getFeatured())
                .thenReturn(new ArrayList<>());

        addonService.getFeatured();

        Mockito.verify(addonRepository, Mockito.times(1)).getFeatured();
    }

    @Test
    public void getNewest_should_callRepository() {
        Mockito.when(addonRepository.getNewest())
                .thenReturn(new ArrayList<>());

        addonService.getNewest();

        Mockito.verify(addonRepository, Mockito.times(1)).getNewest();
    }

    @Test
    public void getPopular_should_callRepository() {
        Mockito.when(addonRepository.getPopular())
                .thenReturn(new ArrayList<>());

        addonService.getPopular();

        Mockito.verify(addonRepository, Mockito.times(1)).getPopular();
    }

    @Test
    public void filter_should_callRepository() {
        Optional<String> name = Optional.empty();
        Optional<Integer> targetIde = Optional.empty();
        Optional<String> sort = Optional.empty();


        Mockito.when(addonRepository.filter(name, targetIde, sort))
                .thenReturn(new ArrayList<>());

        addonService.filter(name, targetIde, sort);

        Mockito.verify(addonRepository, Mockito.times(1)).filter(name, targetIde, sort);
    }
}
