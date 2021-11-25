package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.RepoInfo;

public interface GitHubRestApiConsumer {

    void populateRepoInfoFromApi(String repo_url, RepoInfo repoInfo);

}

