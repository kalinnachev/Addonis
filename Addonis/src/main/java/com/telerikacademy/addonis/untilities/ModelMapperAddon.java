package com.telerikacademy.addonis.untilities;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.AddonDto;
import com.telerikacademy.addonis.models.dto.AddonUpdateDto;
import com.telerikacademy.addonis.repositories.contracts.TagRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ModelMapperAddon {
    private final AddonService addonService;
    private final TargetIdeService targetIdeService;
    private final TagRepository tagRepository;

    public ModelMapperAddon(AddonService addonService,
                            TargetIdeService targetIdeService, TagRepository tagRepository) {
        this.addonService = addonService;
        this.targetIdeService = targetIdeService;
        this.tagRepository = tagRepository;
    }

    public Addon fromDto(AddonDto addonDto, User user){
        Addon addon = new Addon();
        dtoToObject(addon,addonDto,user);
        return addon;
    }

    public Addon fromDto(AddonUpdateDto addonUpdateDto, int id) {
        Addon addon = addonService.getById(id);
        dtoUpdateObject(addonUpdateDto,addon);
        return addon;
    }

    private void dtoToObject(Addon addon, AddonDto addonDto, User user) {
        addon.setName(addonDto.getName());
        addon.setDescription(addon.getDescription());
        addon.setCreator(user);
        addon.setTargetIde(targetIdeService.getById(addonDto.getTargetIde()));
        addon.setOriginUrl(addonDto.getOriginUrl());
        //TODO should make lambda for converting integers in tags using service
        Set<Tag> tags = new HashSet<>();
        for (Integer tag : addonDto.getTags()) {
            Tag tagToAdd = tagRepository.getById(tag);
            tags.add(tagToAdd);
        }
        addon.setTags(tags);
        addon.setBinaryContentUrl(addonDto.getBinaryContentUrl());
        addon.setApproved(false);
        addon.setCreationDate(LocalDate.now());
    }

    //TODO method for update from admin after approving with additional info
    private void dtoUpdateObject(AddonUpdateDto addonUpdateDto, Addon addon) {
        addon.setDescription(addonUpdateDto.getDescription());
        addon.setBinaryContentUrl(addonUpdateDto.getBinaryContentUrl());
        Set<Tag> tags = new HashSet<>();
        for (Integer tag : addonUpdateDto.getTags()) {
            Tag tagToAdd = tagRepository.getById(tag);
            tags.add(tagToAdd);
        }
        addon.setTags(tags);
    }
}
