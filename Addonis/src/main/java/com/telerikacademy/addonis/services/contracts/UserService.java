package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getById(int id);

    List<User> getAll();

    User getByUsername(String username);

    void create(User user, File profilePicture);

    void delete(int id, User user);

    void update(User user, Optional<File> profilePicture);

    User block(int id, User user);

    User unblock(int id, User user);

    List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber, User user);
}
