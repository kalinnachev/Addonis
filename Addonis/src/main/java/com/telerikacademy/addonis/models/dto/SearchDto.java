package com.telerikacademy.addonis.models.dto;

public class SearchDto {

    private String addonName;

    private Integer targetIdeID;

    private Integer sortBy;

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public Integer getTargetIdeID() {
        return targetIdeID;
    }

    public void setTargetIdeID(Integer targetIdeID) {
        this.targetIdeID = targetIdeID;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }
}
