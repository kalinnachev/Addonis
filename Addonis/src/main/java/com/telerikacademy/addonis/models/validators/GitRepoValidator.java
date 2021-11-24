package com.telerikacademy.addonis.models.validators;

import com.telerikacademy.addonis.utils.GitUrlHelpers;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GitRepoValidator implements ConstraintValidator<ValidGitRepo, String> {

    private final RestTemplate restTemplate;

    public GitRepoValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValid(String repoURL, ConstraintValidatorContext cxt) {
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
