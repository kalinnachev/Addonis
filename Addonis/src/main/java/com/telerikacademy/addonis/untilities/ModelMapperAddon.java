package com.telerikacademy.addonis.untilities;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import com.telerikacademy.addonis.services.contracts.UserService;

import java.time.LocalDate;

public class ModelMapperAddon {
    private final AddonService addonService;
    private final TargetIdeService targetIdeService;

    public ModelMapperAddon(AddonService addonService,
                            TargetIdeService targetIdeService) {
        this.addonService = addonService;
        this.targetIdeService = targetIdeService;
    }

    public Addon fromDto(AddonDto addonDto, User user){
        Addon addon = new Addon();
        dtoToObject(addon,addonDto,user);
        return addon;
    }

    private void dtoToObject(Addon addon, AddonDto addonDto, User user) {
        addon.setName(addonDto.getName());
        addon.setTargetIde(targetIdeService.getById(addonDto.getTargetIde()));
        //TODO check on origin_url
        addon.setOriginUrl(addonDto.getOriginUrl());
        addon.setDescription(addon.getDescription());
        addon.setCreator(user);
        //TODO should make lambda for converting integers in tags using service
//        addon.setTags(
//                addonDto.getTags().stream().forEach();
//        );
        addon.setCreationDate(LocalDate.now());
    }

    //TODO method for update from admin after approving with additional info
}
