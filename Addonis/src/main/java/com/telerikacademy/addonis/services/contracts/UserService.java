package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;

import java.util.List;

public interface UserService {
    User getById(int id);

    List<User> getAll();

    void crete(User user);

    void delete(int id);

    void update(User user);
}
