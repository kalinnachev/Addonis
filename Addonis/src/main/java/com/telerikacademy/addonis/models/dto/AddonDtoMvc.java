package com.telerikacademy.addonis.models.dto;



import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;


public class AddonDtoMvc extends AddonDto {
    @NotNull(message = "File can't be empty")
    private MultipartFile binaryFile;

    public MultipartFile getBinaryFile() {
        return binaryFile;
    }

    public void setBinaryFile(MultipartFile binaryFile) {
        this.binaryFile = binaryFile;
    }
}
