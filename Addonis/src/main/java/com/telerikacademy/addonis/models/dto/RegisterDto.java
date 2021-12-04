package com.telerikacademy.addonis.models.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

public class RegisterDto extends UserDto {
    @NotEmpty(message = "Password confirmation can't be empty")
    private String passwordConfirm;

    private MultipartFile multipartFile;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
