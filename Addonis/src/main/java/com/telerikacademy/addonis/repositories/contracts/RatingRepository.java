package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;

public interface RatingRepository extends CRUDRepository<Rating>{

    Rating getByUserAndAddon(Addon addon, User user);
}
