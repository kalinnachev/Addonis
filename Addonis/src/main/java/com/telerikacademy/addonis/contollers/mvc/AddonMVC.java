package com.telerikacademy.addonis.contollers.mvc;


import com.telerikacademy.addonis.events.AddonApprovedEvent;
import com.telerikacademy.addonis.events.AddonRejectedEvent;
import com.telerikacademy.addonis.exceptions.AuthenticationFailureException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.*;
import com.telerikacademy.addonis.models.dto.AddonApprovalDto;
import com.telerikacademy.addonis.models.dto.AddonDtoMvc;
import com.telerikacademy.addonis.models.dto.SearchDto;
import com.telerikacademy.addonis.services.contracts.*;
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
    public AddonMVC(AddonService addonService, AuthenticationHelper authenticationHelper, RatingService ratingService, ModelMapperAddon modelMapperAddon, TargetIdeService targetIdeService, TagService tagService, ApplicationEventPublisher applicationEventPublisher, UserService userService) {
        super(authenticationHelper);
        this.addonService = addonService;
        this.ratingService = ratingService;
        this.modelMapperAddon = modelMapperAddon;
        this.targetIdeService = targetIdeService;
        this.tagService = tagService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @ModelAttribute("allTag")
    public List<Tag> populateTags() {
        return tagService.getAll();
    }

    @ModelAttribute("allTargetIde")
    public List<TargetIde> populateTargetIdes() {
        return targetIdeService.getAll();
    }

    @GetMapping("/{id}")
    public String showSingleAddon(@PathVariable int id, Model model, HttpSession session) {
        Addon addon = addonService.getById(id);
        model.addAttribute("approvalDto", new AddonApprovalDto());
        if (isUserLogged(session))
            model.addAttribute("loggedUserRating", getRatingAsInteger(getLoggedUser(session), addon));
        model.addAttribute("addon", addon);
        return "addon-details";
    }

    @GetMapping()
    public String showAllAddonsOnUserPage(Model model, HttpSession session) {
        try {
            User user = getLoggedUser(session);
            model.addAttribute("addonlist", addonService.getByUser(user.getId()));
            model.addAttribute("user", user);

            return "myaddons";
        } catch (EntityNotFoundException e) {
            return "not_found";
        }
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
    public String rejectAddon(@PathVariable int id, Model model, HttpSession session, @ModelAttribute("approvalDto") AddonApprovalDto approvalDto, BindingResult bindingResult) {
        Addon addon = addonService.getById(id);
        if (approvalDto.getMessage().isBlank()) {
            bindingResult.rejectValue("message", "err",
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
        return "addon-new";
    }

    @PostMapping("/new")
    public String createAddon(@Valid @ModelAttribute("addon") AddonDtoMvc addonDtoMvc, BindingResult errors,
                              HttpSession session) throws IOException {
        if (addonDtoMvc.getBinaryFile().isEmpty()) {
            errors.rejectValue("binaryFile", "err", "Missing binary file");
        }
        if (errors.hasErrors()) {
            return "addon-new";
        }
        User user = getLoggedUser(session);
        Addon addon = modelMapperAddon.fromDto(addonDtoMvc, user);
        addonService.create(addon, user, IOUtils.convert(addonDtoMvc.getBinaryFile()));
        return "redirect:/addons/" + addon.getId();
    }

    @GetMapping("/{id}/update")
    public String showEditAddonPage(@PathVariable int id, Model model, HttpSession session) {
        User user = getLoggedUser(session);
        Addon addon = addonService.getById(id);
        AddonDtoMvc addonDto = modelMapperAddon.toMvcDto(addon);
        model.addAttribute("addonId", id);
        model.addAttribute("addon", addonDto);
        return "addon-update";
    }

    @GetMapping("/{id}/featured")
    public String changeFeaturedProperty(@PathVariable int id, Model model, HttpSession session) {
        User user = getLoggedUser(session);
        Addon addon = addonService.getById(id);
        addon.setFeatured(!addon.isFeatured());
        addonService.update(addon, user, Optional.empty());
        return "redirect:/addons/{id}";
    }

    @PostMapping("/{id}/update")
    public String updateAddon(@PathVariable int id,
                              @Valid @ModelAttribute("addon") AddonDtoMvc addonDto,
                              BindingResult errors,
                              HttpSession session) throws IOException {
        if (errors.hasErrors()) {
            return "addon-update";
        }
        User user = getLoggedUser(session);
        File file = null;
        if (!addonDto.getBinaryFile().isEmpty()) {
            file = IOUtils.convert(addonDto.getBinaryFile());
        }
        Addon addon = modelMapperAddon.fromMvcDto(addonDto, id);
        addonService.update(addon, user, Optional.ofNullable(file));

        return "redirect:/addons/{id}";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("search") SearchDto searchDto) {
        Integer targetId = searchDto.getTargetIdeID() == 0 ? null : searchDto.getTargetIdeID();
        List<Addon> addonList = addonService.filter(
                Optional.ofNullable(searchDto.getAddonName()),
                Optional.ofNullable(targetId),
                Optional.ofNullable(getSortString(searchDto.getSortBy())));
        model.addAttribute("addonList", addonList);

        model.addAttribute("title", getSearchTitle(addonList.size()));
        return "addon-search";
    }

    @GetMapping("/pending")
    public String showPendingApprovalAddons(Model model, HttpSession session) {
        User user = getLoggedUser(session);
        List<Addon> addonList = addonService.getPending(user);
        model.addAttribute("addonList",addonList);
        if(addonList.isEmpty()){
            return "not_found";
        }
        model.addAttribute("title", "Pending Approval");
        return "pending-approval";
    }

    private String getSearchTitle(int size) {
        if (size == 1)
            return "Search results (1 addon):";
        if (size == 0)
            return "No addons found";
        return "Search results (" + size + " addons):";
    }

    private Integer getRatingAsInteger(User user, Addon addon) {
        Integer userRating = null;
        try {
            userRating = ratingService.getByUserAndAddon(addon, user).getRating();
        } catch (AuthenticationFailureException | EntityNotFoundException e) {
        }
        return userRating;
    }

    private void rateAddon(Integer rating, Addon addon, User user) {
        try {
            Rating ratingEntity = ratingService.getByUserAndAddon(addon, user);
            ratingEntity.setRating(rating);
            ratingService.update(ratingEntity);
        } catch (EntityNotFoundException e) {
            Rating ratingEntity = new Rating();
            ratingEntity.setRating(rating);
            ratingEntity.setAddon(addon);
            ratingEntity.setUser(user);
            ratingService.create(ratingEntity);
        }
    }

    private String getSortString(int value){
        switch (value){
            case 1: return "lastCommitDate_desc";
            case 2: return "download_desc";
            case 3: return "uploadDate";
            case 4: return "name_desc";
        }
        return null;
    }
}
