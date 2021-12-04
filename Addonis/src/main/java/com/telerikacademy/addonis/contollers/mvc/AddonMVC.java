package com.telerikacademy.addonis.contollers.mvc;


import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.services.contracts.AddonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/addons")
public class AddonMVC {

    private final AddonService addonService;

    public AddonMVC(AddonService addonService) {
        this.addonService = addonService;
    }

    @GetMapping("/{id}")
    public String showSingleAddon(@PathVariable int id, Model model) {
        try {
            Addon addon = addonService.getById(id);
            model.addAttribute("addon", addon);
            model.addAttribute("vf",new ViewFormatter());
            model.addAttribute("addons",addonService.getAll());
            model.addAttribute("addonsFilterByTargetIde", addonService.filter(Optional.empty(), Optional.of(addon.getTargetIde().getId())
                    ,Optional.empty()));
            return "product";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
