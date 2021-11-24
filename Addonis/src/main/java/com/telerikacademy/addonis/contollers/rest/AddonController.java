package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.services.contracts.AddonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addons")
public class AddonController {
    //TODO add modelMapper and create Dto
    private final AddonService addonService;

    public AddonController(AddonService addonService) {
        this.addonService = addonService;
    }

    //TODO authentication and exception handling

    @GetMapping
    public List<Addon> getAll(){
        return addonService.getAll();
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
    @PostMapping("/{id}")
    public Addon createAddon(){
        throw new UnsupportedOperationException();
    }

    //TODO when Dto is done
    @PutMapping("/{id}")
    public Addon updateAddon(){
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public void deleteAddon(@PathVariable int id){
        addonService.delete(id);
    }

    //TODO the rest of the functionality
}
