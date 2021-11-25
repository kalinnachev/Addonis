package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.ModelMapperAddon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/addons")
public class AddonController {
    private final ModelMapperAddon modelMapperAddon;
    private final AddonService addonService;
    private final UserService userService;

    public AddonController(ModelMapperAddon modelMapperAddon, AddonService addonService, UserService userService) {
        this.modelMapperAddon = modelMapperAddon;
        this.addonService = addonService;
        this.userService = userService;
    }

    //TODO authentication and exception handling

    @GetMapping
    public List<Addon> getAll(){
        return addonService.getAll();
    }

    @GetMapping("/featured")
    public List<Addon> getFeatured(){
        return addonService.getFeatured();
    }

    @GetMapping("/new")
    public List<Addon> getNewest(){
        return addonService.getNewest();
    }

    @GetMapping("/popular")
    public List<Addon> getPopular(){
        return addonService.getPopular();
    }

    @GetMapping("/{id}")
    public Addon getById(@PathVariable int id) {
        try {
            return addonService.getById(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //TODO when Dto is done
    @PostMapping()
    public Addon createAddon(@Valid @RequestBody AddonDto addonDto){
        try{
            //TODO swap user with header when authentication is ready
            User user = userService.getById(1);
            Addon addon = modelMapperAddon.fromDto(addonDto,user);
            addonService.create(addon);
            return addon;
        } catch (DuplicateEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    //TODO when Dto is done
    @PutMapping("/{id}")
    public Addon updateAddon(@PathVariable int id,
                             @Valid @RequestBody AddonUpdateDto addonUpdateDto){
        try{
            //TODO swap user with header when authentication is ready
            User user = userService.getById(1);
            Addon addon = modelMapperAddon.fromDto(addonUpdateDto, id);
            addonService.update(addon);
            return addon;
        } catch (DuplicateEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}/approve")
    public Addon approveAddon(@PathVariable int id){
        //TODO authentication and exception handling
        Addon addon = addonService.getById(id);
        addonService.approve(addon);
        return addon;
    }

    @DeleteMapping("/{id}")
    public void deleteAddon(@PathVariable int id){
        try{
            addonService.delete(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
