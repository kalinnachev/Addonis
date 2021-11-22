package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidPassword;

import javax.validation.constraints.NotNull;

public class UserUpdateDto {
    @ValidPassword
    private String password;

    @NotNull(message = "First name can't be empty")
    private String firstName;

    @NotNull(message = "Last name can't be empty")
    private String lastName;

    @NotNull(message = "Email can't be empty")
    private String email;

    public UserUpdateDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
