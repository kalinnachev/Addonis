package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeMVC extends BaseMvcController {

    private final AddonService addonService;
    private final TargetIdeService targetIdeService;

    @Autowired
    public HomeMVC(AddonService addonService, AuthenticationHelper authenticationHelper, TargetIdeService targetIdeService) {
        super(authenticationHelper);
        this.addonService = addonService;
        this.targetIdeService = targetIdeService;
    }

    @GetMapping
    public String getHome(Model model, HttpSession session) {
        model.addAttribute("featured", addonService.getFeatured());
        model.addAttribute("newest", addonService.getNewest());
        model.addAttribute("popular", addonService.getPopular());
        return "index";
    }

    @ModelAttribute("allTargetIde")
    public List<TargetIde> populateTargetIdes() {
        return targetIdeService.getAll();
    }


}
