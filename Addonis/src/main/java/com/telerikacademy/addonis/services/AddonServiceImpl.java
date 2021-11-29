package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddonServiceImpl implements AddonService {

    private final AddonRepository addonRepository;
    private final RepoInfoService repoInfoService;

    @Autowired
    public AddonServiceImpl(AddonRepository addonRepository, RepoInfoService repoInfoService) {
        this.addonRepository = addonRepository;
        this.repoInfoService = repoInfoService;
    }

    @Override
    public Addon getById(int id) {
        //TODO checks
        return addonRepository.getById(id);
    }

    @Override
    public List<Addon> getAll() {
        return addonRepository.getAll();
    }

    @Override
    public void create(Addon addon) {
        checkForDuplicateOriginUrl(addon);
        repoInfoService.createInfoForAddon(addon);
        addonRepository.create(addon);
    }

    @Override
    public void update(Addon addon) {
        //TODO checks 1.for user same as creator
        addonRepository.update(addon);
    }

    @Override
    public void delete(int id) {
        //TODO checks
        addonRepository.delete(id);
    }

    @Override
    public void approve(Addon addon) {
        addon.setApproved(true);
    }

    @Override
    public List<Addon> getFeatured() {
        return addonRepository.getFeatured();
    }

    @Override
    public List<Addon> getNewest() {
        return addonRepository.getNewest();
    }

    @Override
    public List<Addon> getPopular() {
        return addonRepository.getPopular();
    }

    private void checkForDuplicateOriginUrl(Addon addon) {
        boolean duplicateExist = true;
        try{
            addonRepository.findByOriginUrl(addon.getOriginUrl());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }
        if(duplicateExist) {
            throw new DuplicateEntityException("Addon", "originUrl", addon.getOriginUrl());
        }
    }
}
