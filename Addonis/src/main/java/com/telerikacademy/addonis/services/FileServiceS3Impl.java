package com.telerikacademy.addonis.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceS3Impl implements FileService {

    private static final String BUCKET = "addonis-app";
    private final AmazonS3 amazonS3;

    private Logger logger = LoggerFactory.getLogger(FileServiceS3Impl.class);

    @Autowired
    public FileServiceS3Impl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String storeBinaryContent(MultipartFile file, Addon addon) {

        return null;
    }

    @Override
    public String storeUserPicture(MultipartFile multipartFile, User user) {
        String key = user.getUsername() + "_profile_picture_" + multipartFile.getOriginalFilename();
        user.setPictureUrl(multipartFile.getOriginalFilename());
        try {
            saveFile(multipartFile, key);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
        return multipartFile.getOriginalFilename();
    }

    @Override
    public byte[] getUserPicture(User user) {
        logger.info("Requesting user picture from Amazon S3");
        String key = user.getUsername() + "_profile_picture_" + user.getPictureUrl();
        byte[] content = null;
        try(S3Object s3Object = amazonS3.getObject(BUCKET, key)){
            S3ObjectInputStream stream = s3Object.getObjectContent();
            content = IOUtils.toByteArray(stream);
        } catch(IOException e) {
            // TODO
            e.printStackTrace();
        }
        logger.info("Requesting user picture from Amazon S3 - DONE");

        return content;
    }

    @Override
    public byte[] getBinaryContent(Addon addon) {
        return new byte[0];
    }

    public Resource downloadFile(String fileName) {
        return null;
    }

    public PutObjectResult saveFile(MultipartFile multipartFile, String key) throws IOException {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(multipartFile.getContentType());
        data.setContentLength(multipartFile.getSize());
        PutObjectResult objectResult = amazonS3.putObject(BUCKET, key, multipartFile.getInputStream(), data);
        return objectResult;
    }
}
