package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;

import java.util.List;

public interface AddonService {
    Addon getById(int id);

    List<Addon> getAll();

    void create(Addon addon, User user);

    void update(Addon addon, User user);

    void delete(int id, User user);

    void approve(Addon addon, User user);

    List<Addon> getFeatured();

    List<Addon> getNewest();

    List<Addon> getPopular();
}
