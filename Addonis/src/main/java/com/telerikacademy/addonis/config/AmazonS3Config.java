package com.telerikacademy.addonis.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class AmazonS3Config {

    private final AWSCredentials credentials;

    @Autowired
    public AmazonS3Config(Environment env) {
        credentials = new BasicAWSCredentials(
                env.getProperty("amazon.S3.accesskey"), env.getProperty("amazon.S3.secretkey"));
    }

    @Bean
    public AmazonS3 amazonS3(){
        //set-up the client
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        return s3client;
    }
}
