package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.Rating;
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
        ratingRepository.create(rating);
    }

    @Override
    public Rating getById(int id) {
        return ratingRepository.getById(id);
    }
}
