package com.telerikacademy.addonis.untilities;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.RatingDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.RatingService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperRating {
    private final RatingService ratingService;
    private final AddonService addonService;

    public ModelMapperRating(RatingService ratingService, AddonService addonService) {
        this.ratingService = ratingService;
        this.addonService = addonService;
    }

    public Rating fromDto(int id, RatingDto ratingDto, User user){
        Rating rating = new Rating();
        dtoToObject(ratingDto, user, rating,id);
        return rating;
    }

    private void dtoToObject(RatingDto ratingDto, User user, Rating rating, int id) {
        rating.setAddon(addonService.getById(id));
        rating.setUser(user);
        rating.setRating(ratingDto.getRating());
    }

    public Rating fromDtoUpdate(int id, User user, RatingDto ratingDto) {
        Rating ratingToUpdate = ratingService.getByUserAndAddon(addonService.getById(id), user);
        ratingToUpdate.setRating(ratingDto.getRating());
        return ratingToUpdate;
    }
}
