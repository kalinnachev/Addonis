package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.services.contracts.AddonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMVC {

    private final AddonService addonService;

    public HomeMVC(AddonService addonService) {
        this.addonService = addonService;
    }

    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("test", addonService.getById(15));
        model.addAttribute("vf", new ViewFormatter());

        return "index";
    }
}
