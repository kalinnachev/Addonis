package com.telerikacademy.addonis.contollers.mvc;



import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDtoMvc;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.TagService;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/addons")
public class AddonMVC extends BaseMvcController{

    private final AddonService addonService;
    private final ModelMapperAddon modelMapperAddon;
    private final TargetIdeService targetIdeService;
    private final TagService tagService;

    public AddonMVC(AddonService addonService, AuthenticationHelper authenticationHelper, ModelMapperAddon modelMapperAddon, TargetIdeService targetIdeService, TagService tagService) {
        super(authenticationHelper);
        this.addonService = addonService;
        this.modelMapperAddon = modelMapperAddon;
        this.targetIdeService = targetIdeService;
        this.tagService = tagService;
    }

    @ModelAttribute("addons")
    public List<Addon> populateShipment() {
        return addonService.getAll();
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
        model.addAttribute("allTargetIde",targetIdeService.getAll());
        List<Tag> tags = tagService.getAll();
        model.addAttribute("allTag", tags);
        return "addon-new";
    }

    @PostMapping("/new")
    public String createAddon(@Valid @ModelAttribute("addon") AddonDtoMvc addonDtoMvc, BindingResult errors,
                               HttpSession session) throws IOException {
        if (errors.hasErrors()) {
            return "addon-new";
        }
            User user = getLoggedUser(session);
            Addon addon = modelMapperAddon.fromDto(addonDtoMvc,user);
            addonService.create(addon,user,IOUtils.convert(addonDtoMvc.getBinaryFile()));
        return "/";
    }
    @GetMapping("/{id}/update")
    public String showEditAddonPage(@PathVariable int id, Model model,HttpSession session) {
            User user = getLoggedUser(session);
            Addon addon = addonService.getById(id);
            AddonUpdateDto addonUpdateDto = modelMapperAddon.toDto(addon);
            model.addAttribute("addonId", id);
            model.addAttribute("addon", addonUpdateDto);
            return "addon-update";
    }

    @PostMapping("/{id}/update")
    public String updateAddon(@PathVariable int id,
                               @Valid @ModelAttribute("addon") AddonUpdateDto addonUpdateDto,
                               BindingResult errors,
                               HttpSession session) throws IOException {
        if (errors.hasErrors()) {
            return "addon-update";
        }

            User user = getLoggedUser(session);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto,id);
            File file = IOUtils.convert(addonUpdateDto.getBinaryFile());
            addonService.update(addon,user,Optional.of(file));
        if (getLoggedUser(session).isAdmin())
            return "redirect:/";
        return "redirect:/myaddons";
    }
}
