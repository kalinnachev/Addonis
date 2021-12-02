package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.GitHubRestApiConsumer;
import com.telerikacademy.addonis.utils.GitUrlHelpers;
import org.kohsuke.github.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/*
https://github-api.kohsuke.org
 */
@Repository
@Primary
public class GitHubConsumerImpl implements GitHubRestApiConsumer {

    private Logger logger = LoggerFactory.getLogger(GitHubConsumerImpl.class);

    private final GitHub gitHub;

    @Autowired
    public GitHubConsumerImpl(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    @Override
    public void populateRepoInfoFromApi(String repo_url, RepoInfo repoInfo) {
        try {
            String repo = GitUrlHelpers.extractPath(repo_url);
            GHRepository ghRepository = gitHub.getRepository(repo);
            GHCommit ghCommit = ghRepository.listCommits().iterator().next();

            int numberOfPullRequests = ghRepository.queryPullRequests()
                    .state(GHIssueState.OPEN).list().toList().size();

            repoInfo.setOpenPullRequests(numberOfPullRequests);
            repoInfo.setOpenIssues(ghRepository.getOpenIssueCount() - numberOfPullRequests);
            repoInfo.setLastCommitTitle(ghCommit.getCommitShortInfo().getMessage());
            repoInfo.setLastCommitDate(convertToLocalDate(ghCommit.getCommitDate()));
            //ghCommit.getCommitShortInfo().getCommitDate()
        }catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private LocalDateTime convertToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
