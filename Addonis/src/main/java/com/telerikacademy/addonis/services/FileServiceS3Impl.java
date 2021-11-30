package com.telerikacademy.addonis.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

// TODO add link

@Service
public class FileServiceS3Impl implements FileService {

    private static final String BUCKET = "addonis-app";

    private final AmazonS3 amazonS3;

    private final Logger logger = LoggerFactory.getLogger(FileServiceS3Impl.class);

    @Autowired
    public FileServiceS3Impl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String storeBinaryContent(File file, Addon addon) {
        String key = addon.getId() + "_binary_content_" + file.getName();
        amazonS3.putObject(BUCKET, key, file);
        return file.getName();
    }

    @Override
    public String storeUserPicture(File file, User user) {
        String key = user.getUsername() + "_profile_picture_" + file.getName();
        amazonS3.putObject(BUCKET, key, file);
        return file.getName();
    }

    @Override
    public byte[] getUserPicture(User user) {
        String key = user.getUsername() + "_profile_picture_" + user.getPictureUrl();
        return getContent(key);
    }

    @Override
    public byte[] getBinaryContent(Addon addon) {
        String key = addon.getId() + "_binary_content_" + addon.getBinaryContentUrl();
        return getContent(key);
    }

    private byte[] getContent(String key) {
        logger.info("Requesting data from Amazon S3");
        byte[] content = null;
        try (S3Object s3Object = amazonS3.getObject(BUCKET, key)) {
            S3ObjectInputStream stream = s3Object.getObjectContent();
            content = IOUtils.toByteArray(stream);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
        logger.info("Requesting data from Amazon S3 - DONE");
        return content;
    }


}
