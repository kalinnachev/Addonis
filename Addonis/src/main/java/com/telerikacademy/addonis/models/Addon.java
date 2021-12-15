package com.telerikacademy.addonis.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "addons")
@Entity
public class Addon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_ide_id")
    private TargetIde targetIde;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "description")
    private String description;

    @Column(name = "origin_url")
    private String originUrl;

    @Column(name = "binary_content_url")
    private String binaryContentUrl;

    @Column(name = "number_of_downloads")
    private Integer numberOfDownloads;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "featured")
    private boolean featured;

    @OneToOne
    @JoinColumn(name = "repo_info_id")
    private RepoInfo repoInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "addons_tags", //ratings
            joinColumns = @JoinColumn(name = "addon_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id") //rating
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "addon", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Rating> ratings = new HashSet<>();

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Integer numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getBinaryContentUrl() {
        return binaryContentUrl;
    }

    public void setBinaryContentUrl(String binaryContentUrl) {
        this.binaryContentUrl = binaryContentUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TargetIde getTargetIde() {
        return targetIde;
    }

    public void setTargetIde(TargetIde targetIde) {
        this.targetIde = targetIde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public RepoInfo getRepoInfo() {
        return repoInfo;
    }

    public void setRepoInfo(RepoInfo repoInfo) {
        this.repoInfo = repoInfo;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addon addon = (Addon) o;
        return Objects.equals(id, addon.id) && Objects.equals(name, addon.name);
    }

    public double getAverageRating() {
        if(ratings.isEmpty())
            return 0;
        double totalRating = 0;
        for (Rating rating : ratings) {
            totalRating += rating.getRating();
        }
        double avgRating =totalRating / ratings.size();
        return (double)Math.round(avgRating * 100d) / 100d;

    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}