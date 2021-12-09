package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidGitRepo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class AddonDto {

    public static final int MAX_ADDON_NAME_LENGTH = 30;
    public static final int MIN_ADDON_NAME_LENGTH = 3;
    public static final String NAME_ERR_MSG = "Name should be between 3 and 30 symbols";


    @NotNull(message = "Name can't be empty")
    @Size(min = MIN_ADDON_NAME_LENGTH, max = MAX_ADDON_NAME_LENGTH,
            message = NAME_ERR_MSG)
    private String name;

    @Positive
    private int targetIde;

    @NotNull(message = "Description can't be empty")
    @Size(min = 50, message = "Description should be between minimum 50 symbols")
    private String description;

    @ValidGitRepo
    private String originUrl;

    @Size(min = 1, message = "Must have at least one tag")
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

    public Set<Integer> getTags() {
        return tags;
    }

    public void setTags(Set<Integer> tags) {
        this.tags = tags;
    }
}
