package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.models.User;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ViewFormatter {

    private PrettyTime prettyTime = new PrettyTime();

    public String getAverageRating(Addon addon) {
        if (addon.getAverageRating() == 0)
            return "Not rated yet";
        return String.format("Average rating " + addon.getAverageRating() + " out of 5");
    }

    public long getNumberOfUserByAddonAndRating(Addon addon, int rating){
        return addon.getRatings().stream().filter(r->r.getRating().equals(rating)).count();
    }

    public int getRelativePercentageByAddonAndRating(Addon addon, int rating){
        long count = getNumberOfUserByAddonAndRating(addon, rating);
        double total = addon.getRatings().size();
        if(total == 0)
            return 0;
        return (int) (100*(count/total));
    }

    public String getLastCommit(Addon addon) {
        return "Last commit : " + prettyTime.format(addon.getRepoInfo().getLastCommitDate());
    }

    public String getLastCommitPrettyDate(Addon addon) {
        return prettyTime.format(addon.getRepoInfo().getLastCommitDate());
    }

    public String getCreatedPrettyDate(Addon addon) {
        return prettyTime.format(addon.getCreationDate());
    }

    public String getLinkBinary(Addon addon) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().
                replacePath(String.format("/api/storage/addons/%d/content", addon.getId()))
                .build().toString();
    }

    public String getUserPictureLink(User user) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().
                replacePath(String.format("/api/storage/users/%d/picture", user.getId()))
                .build().toString();
    }

    public int getNumberOfStars(Addon addon) {
        return (int) Math.round(addon.getAverageRating());
    }

    public String getNumberOfDownloads(Addon addon) {
        return addon.getNumberOfDownloads() + " downloads";
    }

    public String getIDEBadgeCSSClass(Addon addon){
        switch (addon.getTargetIde().getName()){
            case "Eclipse": return "ide-eclipse";
            case "IntellijIDE": return "ide-ij";
            case "Visual Studio": return "ide-vs";
            case "Xcode": return "ide-xcode";
        }
        return "ide-default";
    }

    public String getShortDescription(Addon addon){
        String [] split = addon.getDescription().split(" ");
        if(split.length<15) return addon.getDescription();

        return Arrays.stream(split)
                .limit(15)
                .collect(Collectors.joining(" ",""," ... "));
    }
}
