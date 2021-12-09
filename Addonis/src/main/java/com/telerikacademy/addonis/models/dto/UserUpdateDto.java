package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateDto {
    public static final int SIZE_TELEPHONE_NUMBER = 10;
    @ValidPassword
    private String password;

    @NotNull(message = "First name can't be empty")
    private String firstName;

    @NotNull(message = "Last name can't be empty")
    private String lastName;

    @Size(min = 1, message = "Email is mandatory")
    @Email
    private String email;

    @NotNull(message = "Telephone number can't be empty")
    @Size(min = SIZE_TELEPHONE_NUMBER, max = SIZE_TELEPHONE_NUMBER, message = "Telephone number should have ten digit")
    private String phoneNumber;

    public UserUpdateDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
