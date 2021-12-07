package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.RatingRepository;
import com.telerikacademy.addonis.services.contracts.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void create(Rating rating) {
        checkIfExists(rating);
        ratingRepository.create(rating);
    }

    @Override
    public Rating getById(int id) {
        return ratingRepository.getById(id);
    }

    @Override
    public Rating getByUserAndAddon(Addon addon, User user) {
        return ratingRepository.getByUserAndAddon(addon,user);
    }

    @Override
    public void update(Rating ratingToUpdate) {
         ratingRepository.update(ratingToUpdate);
    }

    private void checkIfExists(Rating rating) {
       try {
           Rating rating1 = ratingRepository.getByUserAndAddon(rating.getAddon(), rating.getUser());
           if(rating.equals(rating1)){
               throw new IllegalArgumentException("Already rated. Use re-rate");
           }
       }catch (EntityNotFoundException ignored){}
    }
}
