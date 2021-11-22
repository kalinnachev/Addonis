package com.telerikacademy.addonis.models.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto extends UserUpdateDto {

    public static final int MIN_USERNAME_LENGTH = 2;
    public static final int MAX_USERNAME_LENGTH = 20;
    public static final String USERNAME_ERR_MSG = "Username should be between 2 and 20 symbols";

    @NotNull(message = "Username name can't be empty")
    @Size(min = MIN_USERNAME_LENGTH, max = MAX_USERNAME_LENGTH, message = USERNAME_ERR_MSG)
    private String username;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
