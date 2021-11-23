package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getById(int id);

    List<User> getAll();

    void crete(User user);

    void delete(int id);

    void update(User user);

    User block(int id);

    User unblock(int id);

    List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber);

}
