package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<User> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber);

    User findByTelephone(String phoneNumber);
}
