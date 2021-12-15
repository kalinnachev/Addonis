package com.telerikacademy.addonis.models.validators;

import com.telerikacademy.addonis.untilities.GitUrlHelpers;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GitRepoValidator implements ConstraintValidator<ValidGitRepo, String> {

    private static final String URL_GIT = "https://github.com/";

    private final RestTemplate restTemplate;

    public GitRepoValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValid(String repoURL, ConstraintValidatorContext cxt) {
        if(!repoURL.startsWith(URL_GIT)){
            return false;
        }
        try{
            tryConnectToURL(repoURL);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private void tryConnectToURL(String repoURL) {
        String apiRepoURL = GitUrlHelpers.getApiRepoURL(repoURL);
        restTemplate.getForEntity(apiRepoURL, String.class);
    }
}

