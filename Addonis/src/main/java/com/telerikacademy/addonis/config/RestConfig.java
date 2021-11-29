package com.telerikacademy.addonis.config;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class RestConfig {

    private final String username;
    private final String password;

    @Autowired
    public RestConfig(Environment env) {
        username = env.getProperty("git.api.username");
        password = env.getProperty("git.api.password");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication(username, password).build();
    }

    @Bean
    public GitHub gitHub() {
        GitHub github = null;
        try {
            github = new GitHubBuilder().withOAuthToken(password,username).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return github;
    }
}
