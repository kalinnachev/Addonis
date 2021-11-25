package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.Addon;

import java.util.List;

public interface AddonRepository extends CRUDRepository<Addon> {
    Addon findByOriginUrl(String originUrl);

    List<Addon> getFeatured();
}
