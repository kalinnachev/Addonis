package com.telerikacademy.addonis.models.dto;

import com.telerikacademy.addonis.models.validators.ValidPassword;

import javax.validation.constraints.NotNull;

public class UserUpdatePassword {

    @ValidPassword
    private String oldPassword;

    @ValidPassword
    private String newPassword;

    @ValidPassword
    private String passwordConfirm;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
