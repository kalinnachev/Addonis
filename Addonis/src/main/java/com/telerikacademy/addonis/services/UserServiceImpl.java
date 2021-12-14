package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.exceptions.UnauthorizedFailureException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.UserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final String ONLY_ADMIN_CAN_UNBLOCK = "Only admin can unblock";
    public static final String ONLY_ADMIN_CAN_SEARCH = "Only admin can search";
    public static final String ONLY_ADMIN_CAN_DELETE = "Only admin can delete";
    public static final String ONLY_ADMIN_CAN_BLOCK = "Only admin can block";

    private final UserRepository userRepository;
    private final FileService fileService;
    private final AddonService addonService;


    public UserServiceImpl(UserRepository userRepository,
                           FileService fileService,
                           AddonService addonService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.addonService = addonService;
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void create(User user, File profilePicture) {
        checkForDuplicateUsername(user);
        checkForDuplicateEmail(user);
        checkForDuplicateTelephone(user);

        setProfilePicture(user, profilePicture);
        userRepository.create(user);
    }

    @Override
    public void update(User user, Optional<File> profilePicture) {
        checkForDuplicateEmail(user);
        checkForDuplicateTelephone(user);

        profilePicture.ifPresent(file -> setProfilePicture(user, file));
        userRepository.update(user);
    }

    @Override
    public User block(int id, User user) {
        if (!user.isAdmin()) {
            throw new UnauthorizedFailureException(ONLY_ADMIN_CAN_BLOCK);
        }
        User userBlock = userRepository.getById(id);
        userBlock.setBlocked(true);
        userRepository.update(userBlock);
        return userBlock;
    }

    @Override
    public User unblock(int id, User user) {
        if (!user.isAdmin()) {
            throw new UnauthorizedFailureException(ONLY_ADMIN_CAN_UNBLOCK);
        }
        User userUnblock = userRepository.getById(id);
        userUnblock.setBlocked(false);
        userRepository.update(userUnblock);
        return userUnblock;

    }

    @Override
    public List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber, User user) {
        if (!user.isAdmin()) {
            throw new UnauthorizedFailureException(ONLY_ADMIN_CAN_SEARCH);
        } else if (username.isEmpty() && email.isEmpty() && phoneNumber.isEmpty()) {
            return userRepository.getAll();
        }
        return userRepository.search(username, email, phoneNumber);
    }

    @Override
    public void delete(int id, User user) {
        if (!user.isAdmin()) {
            throw new UnauthorizedFailureException(ONLY_ADMIN_CAN_DELETE);
        }
        userRepository.delete(id);
    }

    public void checkForDuplicateUsername(User user) {
        boolean duplicateExist = true;
        try {
            userRepository.findByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }
        if (duplicateExist) {
            throw new DuplicateEntityException("User", "username", user.getUsername());
        }
    }

    public void checkForDuplicateEmail(User user) {
        try {
            User u = userRepository.findByEmail(user.getEmail());
            if (u.equals(user))
                return;
        } catch (EntityNotFoundException e) {
            return;
        }
        throw new DuplicateEntityException("User", "e-mail", user.getEmail());
    }

    public void checkForDuplicateTelephone(User user) {
        try {
            User u = userRepository.findByTelephone(user.getPhoneNumber());
            if (u.equals(user))
                return;
        } catch (EntityNotFoundException e) {
            return;
        }
        throw new DuplicateEntityException("User", "phoneNumber", user.getPhoneNumber());
    }

    private void setProfilePicture(User user, File profilePicture) {
        String picture = fileService.storeUserPicture(profilePicture, user);
        user.setPictureUrl(picture);
    }
}