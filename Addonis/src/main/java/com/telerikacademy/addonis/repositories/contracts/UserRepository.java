package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.User;

public interface UserRepository extends CRUDRepository<User> {

    User findByUsername(String username);

    User findByEmail(String email);
}
