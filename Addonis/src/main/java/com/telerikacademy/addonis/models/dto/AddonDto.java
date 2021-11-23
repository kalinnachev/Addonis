package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class AddonDto {

    public static final int MAX_ADDON_NAME_LENGTH = 30;
    public static final int MIN_ADDON_NAME_LENGTH = 3;


    @NotNull(message = "Name can't be empty")
    @Size(min = MIN_ADDON_NAME_LENGTH, max = MAX_ADDON_NAME_LENGTH)
    private String name;

    @Positive
    private int targetIde;

    //TODO must get the current logged user
    private User creator;

    private String description;

    private String originUrl;
    //TODO binary content should be uploaded by admin after approving
    //TODO should ask about changes in database because of null fields
    // private String binaryContentUrl;

    //TODO is the user creating the tags or is he choosing
    private Set<Integer> tags = new HashSet<>();

    public AddonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetIde() {
        return targetIde;
    }

    public void setTargetIde(int targetIde) {
        this.targetIde = targetIde;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

//    public String getBinaryContentUrl() {
//        return binaryContentUrl;
//    }
//
//    public void setBinaryContentUrl(String binaryContentUrl) {
//        this.binaryContentUrl = binaryContentUrl;
//    }

    public Set<Integer> getTags() {
        return tags;
    }

    public void setTags(Set<Integer> tags) {
        this.tags = tags;
    }
}
