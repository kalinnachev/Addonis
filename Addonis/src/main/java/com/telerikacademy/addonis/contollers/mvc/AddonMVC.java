package com.telerikacademy.addonis.contollers.mvc;


import com.telerikacademy.addonis.events.AddonApprovedEvent;
import com.telerikacademy.addonis.events.AddonRejectedEvent;
import com.telerikacademy.addonis.exceptions.AuthenticationFailureException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonApprovalDto;
import com.telerikacademy.addonis.models.dto.AddonDtoMvc;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.RatingService;
import com.telerikacademy.addonis.services.contracts.TagService;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
public class AddonMVC extends BaseMvcController {

    private final AddonService addonService;
    private final RatingService ratingService;
    private final ModelMapperAddon modelMapperAddon;
    private final TargetIdeService targetIdeService;
    private final TagService tagService;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    public AddonMVC(AddonService addonService, AuthenticationHelper authenticationHelper, RatingService ratingService, ModelMapperAddon modelMapperAddon, TargetIdeService targetIdeService, TagService tagService, ApplicationEventPublisher applicationEventPublisher) {
        super(authenticationHelper);
        this.addonService = addonService;
        this.ratingService = ratingService;
        this.modelMapperAddon = modelMapperAddon;
        this.targetIdeService = targetIdeService;
        this.tagService = tagService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // TODO tova za kakvo ni e?
    @ModelAttribute("addons")
    public List<Addon> populateShipment() {
        return addonService.getAll();
    }

    @GetMapping("/{id}")
    public String showSingleAddon(@PathVariable int id, Model model, HttpSession session) {
        Addon addon = addonService.getById(id);
        model.addAttribute("approvalDto", new AddonApprovalDto());
        model.addAttribute("currentrating", getRatingAsInteger(getLoggedUser(session), addon));
        model.addAttribute("addon", addon);
        return "addon-details";
    }

    @PostMapping("/{id}/approve")
    public String approveAddon(@PathVariable int id, HttpSession session, @ModelAttribute("approvalDto") AddonApprovalDto approvalDto) {
        Addon addon = addonService.getById(id);
        User user = getLoggedUser(session);
        addonService.approve(addon, user);
        applicationEventPublisher.publishEvent(new AddonApprovedEvent(addon, approvalDto.getMessage()));
        return "redirect:/addons/" + id;
    }

    @PostMapping("/{id}")
    public String rejectAddon( @PathVariable int id, Model model, HttpSession session, @ModelAttribute("approvalDto") AddonApprovalDto approvalDto, BindingResult bindingResult) {
        Addon addon = addonService.getById(id);
        if(approvalDto.getMessage().isBlank()){
            bindingResult.rejectValue("message","err",
                    "Message is mandatory in case of rejection");
            model.addAttribute("approvalDto", approvalDto);
            model.addAttribute("currentrating", getRatingAsInteger(getLoggedUser(session), addon));
            model.addAttribute("addon", addon);
            return "addon-details";
        }
        //TODO
        // addonService.approve(addon, user);
        applicationEventPublisher.publishEvent(new AddonRejectedEvent(addon, approvalDto.getMessage()));
        return "redirect:/addons/" + id;
    }

    @PostMapping("/{id}/rate")
    public String rateAddon(@PathVariable int id, Model model,
                            @RequestParam(required = false) Optional<Integer> rating,
                            HttpSession session) {
        Addon addon = addonService.getById(id);
        if (rating.isPresent()) {
            User user = getLoggedUser(session);
            rateAddon(rating.get(), addon, user);
            return "redirect:/addons/" + id;
        }
        return "redirect:/addons/" + id;
    }

    @GetMapping("/new")
    public String showNewAddonPage(Model model) {
        model.addAttribute("addon", new AddonDtoMvc());
        model.addAttribute("allTargetIde", targetIdeService.getAll());
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
        Addon addon = modelMapperAddon.fromDto(addonDtoMvc, user);
        addonService.create(addon, user, IOUtils.convert(addonDtoMvc.getBinaryFile()));
        return "/";
    }

    @GetMapping("/{id}/update")
    public String showEditAddonPage(@PathVariable int id, Model model, HttpSession session) {
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
        Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
        File file = IOUtils.convert(addonUpdateDto.getBinaryFile());
        addonService.update(addon, user, Optional.of(file));
        if (getLoggedUser(session).isAdmin())
            return "redirect:/";
        return "redirect:/myaddons";
    }

    private Integer getRatingAsInteger(User user, Addon addon) {
        Integer userRating = null;
        try {
            userRating = ratingService.getByUserAndAddon(addon,user).getRating();
        } catch (AuthenticationFailureException | EntityNotFoundException e){}
        return userRating;
    }

    private void rateAddon(Integer rating, Addon addon, User user) {
        try {
            Rating ratingEntity = ratingService.getByUserAndAddon(addon, user);
            ratingEntity.setRating(rating);
            ratingService.update(ratingEntity);
        } catch (EntityNotFoundException e){
            Rating ratingEntity = new Rating();
            ratingEntity.setRating(rating);
            ratingEntity.setAddon(addon);
            ratingEntity.setUser(user);
            ratingService.create(ratingEntity);
        }
    }
}
