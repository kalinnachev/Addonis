package com.telerikacademy.addonis.models.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

public class UserUpdateMvc {
    private String username;

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
