package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/addons")
public class AddonController {
    public static final String ERROR_MSG_BLOCKED = "Action is blocked";
    public static final String ERROR_MSG_ADMIN = "User must be admin";
    private final ModelMapperAddon modelMapperAddon;
    private final AddonService addonService;
    private final AuthenticationHelper authenticationHelper;

    public AddonController(ModelMapperAddon modelMapperAddon, AddonService addonService, UserService userService, AuthenticationHelper authenticationHelper) {
        this.modelMapperAddon = modelMapperAddon;
        this.addonService = addonService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
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

    //TODO when Dto is done
    @PostMapping()
    public Addon createAddon(@Valid @RequestBody AddonDto addonDto, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        checkIfBlocked(user);
        Addon addon = modelMapperAddon.fromDto(addonDto, user);
        // TODO
        addon.setBinaryContentUrl("test");
        addonService.create(addon);
        return addon;
    }

    @PutMapping("/{id}")
    public Addon updateAddon(@PathVariable int id,
                             @Valid @RequestBody AddonUpdateDto addonUpdateDto,
                             @RequestHeader HttpHeaders headers) {
            User user = authenticationHelper.tryGetUser(headers);
            checkIfBlocked(user);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
            addonService.update(addon);
            return addon;
    }

    @PutMapping("/{id}/approve")
    public Addon approveAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        checkIfAdmin(user);
        Addon addon = addonService.getById(id);
        addonService.approve(addon);
        return addon;
    }

    @DeleteMapping("/{id}")
    public void deleteAddon(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        checkIfBlocked(user);
        addonService.delete(id);

    }

    private void checkIfAdmin(User user) {
        if(!user.isAdmin()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_MSG_ADMIN);
        }
    }

    private void checkIfBlocked(User user) {
        if(!user.isBlocked()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_MSG_BLOCKED);
        }
    }
}
