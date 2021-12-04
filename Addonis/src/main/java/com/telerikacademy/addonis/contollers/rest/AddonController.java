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
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import com.telerikacademy.addonis.untilities.ModelMapperRating;
import io.swagger.annotations.ApiOperation;
import org.joda.time.LocalDate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @ApiOperation(value = "Get all addons")
    @GetMapping
    public List<Addon> getAll() {
        return addonService.getAll();
    }

    @ApiOperation(value = "Filter addons by name and/or targetIde.Sort by uploadDate or downloads " +
            "or last commit date or name")
    @GetMapping("/filter")
    public List<Addon> filterAddon(@RequestParam(required = false) Optional<String> name,
                                   @RequestParam(required = false) Optional<Integer> targetIdeId,
                                   @RequestParam(required = false) Optional<String> sort){
       return addonService.filter(name, targetIdeId, sort);
    }

    @ApiOperation(value = "Get all featured addons")
    @GetMapping("/featured")
    public List<Addon> getFeatured() {
        return addonService.getFeatured();
    }

    @ApiOperation(value = "Get five newest addons")
    @GetMapping("/new")
    public List<Addon> getNewest() {
        return addonService.getNewest();
    }

    @ApiOperation(value = "Get five popular addons")
    @GetMapping("/popular")
    public List<Addon> getPopular() {
        return addonService.getPopular();
    }

    @ApiOperation(value = "Get addon by id")
    @GetMapping("/{id}")
    public Addon getById(@PathVariable int id) {
        return addonService.getById(id);
    }

    @ApiOperation(value = "Create new addon")
    @PostMapping()
    public Addon createAddon(@Valid @RequestPart("addon") AddonDto addonDto,
                             @RequestPart("binary") MultipartFile binary,
                             @RequestHeader HttpHeaders headers) throws IOException {
        User user = authenticationHelper.tryGetUser(headers);
        Addon addon = modelMapperAddon.fromDto(addonDto, user);
        addonService.create(addon,user, IOUtils.convert(binary));
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

    @PutMapping("{id}/re-rate")
    public Addon updateRating(@PathVariable int id,
                              @RequestBody RatingDto ratingDto,
                              @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        Rating ratingToUpdate = modelMapperRating.fromDtoUpdate(id,user,ratingDto);
        ratingService.update(ratingToUpdate);
        return addonService.getById(id);
    }

    @ApiOperation(value = "Update existing addon with the given id")
    @PutMapping("/{id}")
    public Addon updateAddon(@PathVariable int id,
                             @Valid @RequestBody AddonUpdateDto addonUpdateDto,
                             @RequestHeader HttpHeaders headers) {
            User user = authenticationHelper.tryGetUser(headers);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
            addonService.update(addon,user, Optional.empty());
            return addon;
    }

    @ApiOperation(value = "Approve existing addon with the given id")
    @PutMapping("/{id}/approve")
    public Addon approveAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        Addon addon = addonService.getById(id);
        addonService.approve(addon,user);
        return addon;
    }

    @ApiOperation(value = "Delete addon")
    @DeleteMapping("/{id}")
    public void deleteAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        addonService.delete(id,user);
    }

}
