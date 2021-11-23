package com.telerikacademy.addonis.models;

import java.time.LocalDate;

public class AddonGitInfo {

    private LocalDate lastCommitDate;

    private String lastCommitTitle;

    private int pullRequestsCount;

    private int openIssuesCount;

    public LocalDate getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(LocalDate lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }

    public String getLastCommitTitle() {
        return lastCommitTitle;
    }

    public void setLastCommitTitle(String lastCommitTitle) {
        this.lastCommitTitle = lastCommitTitle;
    }

    public int getPullRequestsCount() {
        return pullRequestsCount;
    }

    public void setPullRequestsCount(int pullRequestsCount) {
        this.pullRequestsCount = pullRequestsCount;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }
}
