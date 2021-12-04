package com.telerikacademy.addonis.models.dto;

import javax.validation.constraints.Size;

public class RatingDto {
    @Size(min = 1, max = 5, message = "Rating can be only from 1 to 5")
    private int rating;

    public RatingDto() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
