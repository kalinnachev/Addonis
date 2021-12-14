package com.telerikacademy.addonis.models.dto;

public class UserSearchDto {
    private Integer searchBy;

    private String param;

    public Integer getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(Integer searchBy) {
        this.searchBy = searchBy;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
