package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeMVC extends BaseMvcController{

    private final AddonService addonService;

    @Autowired
    public HomeMVC(AddonService addonService, AuthenticationHelper authenticationHelper) {
        super(authenticationHelper);
        this.addonService = addonService;
    }

    @GetMapping
    public String getHome(Model model, HttpSession session) {
        model.addAttribute("featured", addonService.getFeatured());
        model.addAttribute("newest", addonService.getNewest());
        model.addAttribute("popular", addonService.getPopular());
        return "index";
    }

}
