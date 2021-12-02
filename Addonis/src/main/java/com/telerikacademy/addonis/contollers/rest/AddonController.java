package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/addons")
public class AddonController {

    private final ModelMapperAddon modelMapperAddon;
    private final AddonService addonService;
    private final AuthenticationHelper authenticationHelper;

    public AddonController(ModelMapperAddon modelMapperAddon, AddonService addonService, AuthenticationHelper authenticationHelper) {
        this.modelMapperAddon = modelMapperAddon;
        this.addonService = addonService;
        this.authenticationHelper = authenticationHelper;
    }

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
    public Addon createAddon(@Valid @RequestPart("addon") AddonDto addonDto,
                             @RequestPart("binary") MultipartFile binary,
                             @RequestHeader HttpHeaders headers) throws IOException {
        User user = authenticationHelper.tryGetUser(headers);
        Addon addon = modelMapperAddon.fromDto(addonDto, user);
        addonService.create(addon,user, IOUtils.convert(binary));
        return addon;
    }

    @PutMapping("/{id}")
    public Addon updateAddon(@PathVariable int id,
                             @Valid @RequestBody AddonUpdateDto addonUpdateDto,
                             @RequestHeader HttpHeaders headers) {
            User user = authenticationHelper.tryGetUser(headers);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
            addonService.update(addon,user, Optional.empty());
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
