package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.TargetIde;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ViewFormatter {

    PrettyTime prettyTime = new PrettyTime();

    public String getAverageRating(Addon addon){
        if(addon.getAverageRating() == 0)
            return "Not rated yet";
        return String.format("Average rating "+addon.getAverageRating() + " out of 5");
    }

    public String getLogoSource(TargetIde ide){
        return "./img/"+ ide.getLogo();
    }

    public String getLastCommit(Addon addon){
        return "Last commit : " + prettyTime.format(addon.getRepoInfo().getLastCommitDate());
    }

    public int getNumberOfStars(Addon addon){
        return (int) Math.round(addon.getAverageRating());
    }

    public String getNumberOfDownloads(Addon addon){

        return addon.getNumberOfDownloads() + " downloads";
    }

    public String getAddonDownloadLink(Addon addon){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .replacePath(
                        String.format("api/storage/addons/%d/content", addon.getId()))
                .build()
                .toUriString();
    }

}
