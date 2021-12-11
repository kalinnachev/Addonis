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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/*
https://www.baeldung.com/spring-boot-evict-cache
 */
@Service
public class FileServiceS3Impl implements FileService {

    private static final String BUCKET = "addonis-app";
    private static final String USER_PICTURES_CACHE = "user-pictures";
    private static final String USER_PICTURES_KEY_PART = "_profile_picture_";
    private static final String ADDON_BINARY_KEY_PART = "_binary_content_";


    private final AmazonS3 amazonS3;

    private final Logger logger = LoggerFactory.getLogger(FileServiceS3Impl.class);

    @Autowired
    public FileServiceS3Impl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String storeBinaryContent(File file, Addon addon) {
        String key = addon.getName() + ADDON_BINARY_KEY_PART + file.getName();
        amazonS3.putObject(BUCKET, key, file);
        return file.getName();
    }

    @Override
    @CacheEvict(value = USER_PICTURES_CACHE, key = "#user")
    public String storeUserPicture(File file, User user) {
        String key = user.getUsername() + USER_PICTURES_KEY_PART + file.getName();
        amazonS3.putObject(BUCKET, key, file);
        return file.getName();
    }

    @Override
    @Cacheable(USER_PICTURES_CACHE)
    public byte[] getUserPicture(User user) {
        String key = user.getUsername() + USER_PICTURES_KEY_PART + user.getPictureUrl();
        return getContent(key);
    }

    @Override
    public byte[] getBinaryContent(Addon addon) {
        String key = addon.getName() + ADDON_BINARY_KEY_PART + addon.getBinaryContentUrl();
        return getContent(key);
    }

    private byte[] getContent(String key) {
        logger.info("Requesting data from Amazon S3 for key [{}]", key);
        byte[] content = null;
        try (S3Object s3Object = amazonS3.getObject(BUCKET, key)) {
            S3ObjectInputStream stream = s3Object.getObjectContent();
            content = IOUtils.toByteArray(stream);
        } catch (IOException e) {
            // TODO
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Requesting data from Amazon S3 - DONE");
        return content;
    }


}
