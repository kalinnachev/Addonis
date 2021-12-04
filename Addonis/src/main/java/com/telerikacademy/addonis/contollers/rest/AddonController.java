package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.models.dto.RatingDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.RatingService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import com.telerikacademy.addonis.untilities.ModelMapperRating;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/addons")
public class AddonController {

    private final ModelMapperAddon modelMapperAddon;
    private final AddonService addonService;
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapperRating modelMapperRating;
    private final RatingService ratingService;

    public AddonController(ModelMapperAddon modelMapperAddon,
                           AddonService addonService,
                           AuthenticationHelper authenticationHelper,
                           ModelMapperRating modelMapperRating,
                           RatingService ratingService) {
        this.modelMapperAddon = modelMapperAddon;
        this.addonService = addonService;
        this.authenticationHelper = authenticationHelper;
        this.modelMapperRating = modelMapperRating;
        this.ratingService = ratingService;
    }

    //TODO authentication and exception handling

    @GetMapping
    public List<Addon> getAll() {
        return addonService.getAll();
    }

    @GetMapping("/featured")
    public List<Addon> getFeatured() {
        return addonService.getFeatured();
    }

    @GetMapping("/new")
    public List<Addon> getNewest() {
        return addonService.getNewest();
    }

    @GetMapping("/popular")
    public List<Addon> getPopular() {
        return addonService.getPopular();
    }

    @GetMapping("/{id}")
    public Addon getById(@PathVariable int id) {
        return addonService.getById(id);
    }

    @PostMapping()
    public Addon createAddon(@Valid @RequestBody AddonDto addonDto, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        Addon addon = modelMapperAddon.fromDto(addonDto, user);
        // TODO
        addon.setBinaryContentUrl("test");
        addonService.create(addon,user);
        return addon;
    }

    @PostMapping("/{id}/rate")
    public Addon rateAddon(@PathVariable int id,
                           @RequestBody RatingDto ratingDto,
                           @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        Rating rating = modelMapperRating.fromDto(id,ratingDto,user);
        ratingService.create(rating);
        return addonService.getById(id);
    }



    @PutMapping("/{id}")
    public Addon updateAddon(@PathVariable int id,
                             @Valid @RequestBody AddonUpdateDto addonUpdateDto,
                             @RequestHeader HttpHeaders headers) {
            User user = authenticationHelper.tryGetUser(headers);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
            addonService.update(addon,user);
            return addon;
    }

    @PutMapping("/{id}/approve")
    public Addon approveAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        Addon addon = addonService.getById(id);
        addonService.approve(addon,user);
        return addon;
    }

    @DeleteMapping("/{id}")
    public void deleteAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        addonService.delete(id,user);

    }

}
