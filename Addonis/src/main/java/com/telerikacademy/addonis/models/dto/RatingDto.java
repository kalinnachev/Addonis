package com.telerikacademy.addonis.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingDto {

    @Min(value = 1,message = "Rating can't be less than 1")
    @Max(value = 5, message = "Rating can't be more than 5" )
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
