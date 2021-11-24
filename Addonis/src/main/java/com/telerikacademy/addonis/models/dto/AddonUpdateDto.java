package com.telerikacademy.addonis.models.dto;

import java.util.HashSet;
import java.util.Set;

public class AddonUpdateDto {

    private String description;

    private String binaryContentUrl;

    private Set<Integer> tags = new HashSet<>();

    public AddonUpdateDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBinaryContentUrl() {
        return binaryContentUrl;
    }

    public void setBinaryContentUrl(String binaryContentUrl) {
        this.binaryContentUrl = binaryContentUrl;
    }

    public Set<Integer> getTags() {
        return tags;
    }

    public void setTags(Set<Integer> tags) {
        this.tags = tags;
    }
}
