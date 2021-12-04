package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDto {

    public static final int MIN_USERNAME_LENGTH = 2;
    public static final int MAX_USERNAME_LENGTH = 20;
    public static final String USERNAME_ERR_MSG = "Username should be between 2 and 20 symbols";

    @NotEmpty
    @Size(min = MIN_USERNAME_LENGTH, max = MAX_USERNAME_LENGTH, message = USERNAME_ERR_MSG)
    private String username;

    @NotEmpty
    @ValidPassword
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
