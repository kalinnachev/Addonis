package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotEmpty
    private String username;

    @NotEmpty
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
