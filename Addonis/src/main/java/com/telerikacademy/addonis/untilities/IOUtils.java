package com.telerikacademy.addonis.untilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class IOUtils {

    public static File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir")  +
                File.separator + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }
}
