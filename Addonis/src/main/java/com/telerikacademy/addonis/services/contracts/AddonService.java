package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface AddonService {
    Addon getById(int id);

    List<Addon> getAll();

    void create(Addon addon, User user, File binaryContent);

    void update(Addon addon, User user, Optional<File> binaryContent);

    void delete(int id, User user);

    void approve(Addon addon, User user);

    void reject(Addon addon, User user);

    List<Addon> getFeatured();

    List<Addon> getNewest();

    List<Addon> getPopular();

    List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId, Optional<String> sort);

    List<Addon> getByUser(Integer userID ,Optional<User> loggedUser);

    void update(Addon addon);

    List<Addon> getPending(User user);

}
