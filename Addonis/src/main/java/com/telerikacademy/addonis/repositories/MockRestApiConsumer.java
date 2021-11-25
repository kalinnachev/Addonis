package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.GitHubRestApiConsumer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Primary
public class MockRestApiConsumer implements GitHubRestApiConsumer {
    @Override
    public void populateRepoInfoFromApi(String repo_url, RepoInfo repoInfo) {
        repoInfo.setOpenIssues(1);
        repoInfo.setOpenPullRequests(1);
        repoInfo.setLastCommitDate(LocalDate.now());
        repoInfo.setLastCommitTitle("Mock commit title");
    }
}
