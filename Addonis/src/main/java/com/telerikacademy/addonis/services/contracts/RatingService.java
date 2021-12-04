package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Rating;

public interface RatingService {
    void create(Rating rating);

    Rating getById(int id);
}
