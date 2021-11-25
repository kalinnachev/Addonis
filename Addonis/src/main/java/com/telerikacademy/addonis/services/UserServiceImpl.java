package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import com.telerikacademy.addonis.services.contracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void create(User user) {
        checkForDuplicateUsername(user);
        checkForDuplicateEmail(user);
        checkForDuplicateTelephone(user);
        userRepository.create(user);
    }

    @Override
    public void update(User user) {
        checkForDuplicateEmail(user);
        checkForDuplicateTelephone(user);
        userRepository.update(user);
    }

    @Override
    public User block(int id, User user) {
        if (!user.isAdmin()) {
            throw new IllegalArgumentException("Only admin can block");
        }
        User userBlock = userRepository.getById(id);
        userBlock.setBlocked(true);
        userRepository.update(userBlock);
        return userBlock;
    }

    @Override
    public User unblock(int id, User user) {
        if (!user.isAdmin()) {
            throw new IllegalArgumentException("Only admin can unblock");
        }
        User userUnblock = userRepository.getById(id);
        userUnblock.setBlocked(false);
        userRepository.update(userUnblock);
        return userUnblock;

    }

    @Override
    public List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber, User user) {
        if (!user.isAdmin()) {
            throw new IllegalArgumentException("Only admin can search");
        } else if (username.isEmpty() && email.isEmpty() && phoneNumber.isEmpty()) {
            return userRepository.getAll();
        }
        return userRepository.search(username, email, phoneNumber);
    }

    @Override
    public void delete(int id, User user) {
        if (!user.isAdmin()) {
            throw new IllegalArgumentException("Only admin can delete");
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
            if (u.getId() == user.getId())
                return;
        } catch (EntityNotFoundException e) {
            return;
        }
        throw new DuplicateEntityException("User", "e-mail", user.getEmail());
    }

    public void checkForDuplicateTelephone(User user) {
        try {
            User u = userRepository.findByTelephone(user.getPhoneNumber());
            if (u.getId() == user.getId())
                return;
        } catch (EntityNotFoundException e) {
            return;
        }
        throw new DuplicateEntityException("User", "phoneNumber", user.getPhoneNumber());
    }
}

