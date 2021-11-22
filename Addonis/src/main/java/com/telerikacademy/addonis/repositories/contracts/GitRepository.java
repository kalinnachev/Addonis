package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.AddonGitInfo;

public interface GitRepository {

    AddonGitInfo getInfo(String repo_url);

}

