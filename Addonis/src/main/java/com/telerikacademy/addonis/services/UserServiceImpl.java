package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import com.telerikacademy.addonis.services.contracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void crete(User user) {
        checkForDuplicateUsername(user);
        checkForDuplicateEmail(user);
        userRepository.create(user);
    }

    @Override
    public void update(User user) {
        checkForDuplicateEmail(user);
        userRepository.update(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }


    public void checkForDuplicateUsername(User user){
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

    public void checkForDuplicateEmail(User user){
        boolean duplicateExist = true;
        try {
            userRepository.findByEmail(user.getEmail());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }
        if (duplicateExist) {
            throw new DuplicateEntityException("User", "email", user.getEmail());
        }
    }
}

