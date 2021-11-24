package com.telerikacademy.addonis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
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

}