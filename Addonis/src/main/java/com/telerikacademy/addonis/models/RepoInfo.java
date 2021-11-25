package com.telerikacademy.addonis.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "repo_info")
@Entity
public class RepoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_commit_title")
    private String lastCommitTitle;

    @Column(name = "open_pull_requests")
    private Integer openPullRequests;

    @Column(name = "open_issues")
    private Integer openIssues;

    @Column(name = "last_commit_date")
    private LocalDate lastCommitDate;

    @Column(name = "last_refresh")
    private LocalDateTime lastUpdateDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastCommitTitle() {
        return lastCommitTitle;
    }

    public void setLastCommitTitle(String lastCommitTitle) {
        this.lastCommitTitle = lastCommitTitle;
    }

    public Integer getOpenPullRequests() {
        return openPullRequests;
    }

    public void setOpenPullRequests(Integer openPullRequests) {
        this.openPullRequests = openPullRequests;
    }

    public Integer getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public LocalDate getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(LocalDate lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }
}
