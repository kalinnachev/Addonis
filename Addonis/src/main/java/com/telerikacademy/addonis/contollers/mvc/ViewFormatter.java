package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.TargetIde;
import org.ocpsoft.prettytime.PrettyTime;

public class ViewFormatter {

    PrettyTime prettyTime = new PrettyTime();

    public String getAverageRating(Addon addon){
        return String.format("Average rating "+addon.getAverageRating());
    }

    public String getLogoSource(TargetIde ide){
        return "./img/"+ ide.getLogo();
    }

    public String getLastCommit(Addon addon){
        return "Last commit : " + prettyTime.format(addon.getRepoInfo().getLastCommitDate());
    }

}
