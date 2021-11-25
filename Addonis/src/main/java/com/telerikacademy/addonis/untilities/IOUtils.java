package com.telerikacademy.addonis.untilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtils {

    public static File convert(MultipartFile file) throws IOException, IOException {
        String tmpDirsLocation = System.getProperty("java.io.tmpdir");
        File convFile = new File(tmpDirsLocation + file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
