package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;

public interface RatingService {
    void create(Rating rating);

    Rating getById(int id);

    Rating getByUserAndAddon(Addon byId, User user);

    void update(Rating ratingToUpdate);
}
