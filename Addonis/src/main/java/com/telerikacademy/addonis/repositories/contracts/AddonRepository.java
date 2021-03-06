package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AddonRepository extends CRUDRepository<Addon> {
    Addon findByOriginUrl(String originUrl);

    List<Addon> getFeatured();

    List<Addon> getNewest();

    List<Addon> getPopular();

    Addon getByName(String name);

    List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId, Optional<String> sort);


    List<Addon> getByUser(int userId);

    List<Addon> getPending();


}
