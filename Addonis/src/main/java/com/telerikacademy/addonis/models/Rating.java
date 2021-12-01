package com.telerikacademy.addonis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;

import javax.persistence.*;

@Table(name = "ratings")
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //from Rating where addon = :id return list - ratingList
    // AVG(ratingLIst) = getRating

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "addon_id")
    private Addon addon;

    @Column(name = "rating")
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Addon getAddon() {
        return addon;
    }

    public void setAddon(Addon addon) {
        this.addon = addon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}