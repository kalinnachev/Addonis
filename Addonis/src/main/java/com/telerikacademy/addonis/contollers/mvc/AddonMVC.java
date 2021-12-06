package com.telerikacademy.addonis.contollers.mvc;



import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.dto.AddonDtoMvc;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/addons")
public class AddonMVC extends BaseMvcController{

    private final AddonService addonService;
    private final ModelMapperAddon modelMapperAddon;

    public AddonMVC(AddonService addonService, AuthenticationHelper authenticationHelper, ModelMapperAddon modelMapperAddon) {
        super(authenticationHelper);
        this.addonService = addonService;
        this.modelMapperAddon = modelMapperAddon;
    }

    @GetMapping("/{id}")
    public String showSingleAddon(@PathVariable int id, Model model) {
            Addon addon = addonService.getById(id);
            model.addAttribute("addon", addon);
            model.addAttribute("vf",new ViewFormatter());
            model.addAttribute("addons",addonService.getAll());
            model.addAttribute("addonsFilterByTargetIde", addonService.filter(Optional.empty(), Optional.of(addon.getTargetIde().getId())
                    ,Optional.empty()));
            return "product";
    }

    @GetMapping("/new")
    public String showNewAddonPage(Model model) {
        model.addAttribute("addon", new AddonDtoMvc());
        return "addon-new";
    }

//    @PostMapping("/new")
//    public String createAddon(@Valid @ModelAttribute("addon") AddonDtoMvc addonDtoMvc, BindingResult errors,
//                              Model model, HttpSession session) {
//        if (errors.hasErrors()) {
//            return "addon-new";
//        }
//            User user = getLoggedUser(session);
////            Addon addon = modelMapperAddon.fromDto(addonDto,user);
////            addonService.create();
//    }
}
