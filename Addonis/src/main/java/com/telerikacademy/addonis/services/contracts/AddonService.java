package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;

import java.util.List;

public interface AddonService {
    Addon getById(int id);

    List<Addon> getAll();

    void create(Addon addon);

    void update(Addon addon);

    void delete(int id);

    void approve(Addon addon);

    List<Addon> getFeatured();

    List<Addon> getNewest();
}
