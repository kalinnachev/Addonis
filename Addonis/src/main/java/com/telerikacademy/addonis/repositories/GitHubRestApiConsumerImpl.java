package com.telerikacademy.addonis.repositories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.GitHubRestApiConsumer;
import com.telerikacademy.addonis.utils.GitUrlHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    https://stackabuse.com/spring-boot-guide-to-resttemplate/
    https://www.baeldung.com/rest-template
    https://www.section.io/engineering-education/spring-boot-rest-template/
 */

public class GitHubRestApiConsumerImpl implements GitHubRestApiConsumer {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;

    private final static int MAX_ITEMS_PER_PAGE =100;
    private final static int START_PAGE =1;


    @Autowired
    public GitHubRestApiConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
    }

    @Override
    public void populateRepoInfoFromApi(String repo_url, RepoInfo info) {
        String lastCommitUrl = GitUrlHelpers.getLastCommitURL(repo_url);
        String allIssuesURL = GitUrlHelpers.getAllIssuesURL(repo_url);
        try {
            List<IssueDto> issues = readAllIssues(allIssuesURL, START_PAGE);
            int num_pull_request = (int)issues.stream().filter(IssueDto::isPullRequest).count();
            populateLastCommitDateAndTitle(info, lastCommitUrl);
            info.setOpenIssues(issues.size()-num_pull_request);
            info.setOpenPullRequests(num_pull_request);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    private List<IssueDto> readAllIssues(String apiUrl, int page) throws JsonProcessingException {
        List<IssueDto> issues =  new ArrayList<>(Arrays.asList(restTemplate.getForObject(apiUrl+"&page="+page, IssueDto[].class)));
        if(issues.size() == MAX_ITEMS_PER_PAGE){
            issues.addAll(readAllIssues(apiUrl, ++page));
        }
        return issues;
    }

    private void populateLastCommitDateAndTitle(RepoInfo repoInfo, String apiUrl) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode commitNode = root.get(0).get("commit");
        String commitTitle = commitNode.get("message").asText();
        String commitDate =  commitNode.get("author").get("date").asText().substring(0,10);
        LocalDate date = LocalDate.parse( commitDate, dateTimeFormatter);
        repoInfo.setLastCommitDate(date);
        repoInfo.setLastCommitTitle(commitTitle);
    }

    private void checkLimits(){
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.github.com/rate_limit", String.class);
        System.out.println("response = " + response);

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class IssueDto {

        @JsonProperty("pull_request")
        private Object pull_request;

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Object getPull_request() {
            return pull_request;
        }

        public void setPull_request(Object pull_request) {
            this.pull_request = pull_request;
        }

        public boolean isPullRequest() {
            return pull_request!=null;
        }

    }

}




