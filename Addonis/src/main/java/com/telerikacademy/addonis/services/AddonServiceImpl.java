package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddonServiceImpl implements AddonService {

    private final AddonRepository addonRepository;

    public AddonServiceImpl(AddonRepository addonRepository) {
        this.addonRepository = addonRepository;
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
        checkForDuplicateAddon(addon);
        addonRepository.create(addon);
    }

    @Override
    public void update(Addon addon) {
        //TODO checks
        addonRepository.update(addon);
    }

    @Override
    public void delete(int id) {
        //TODO checks
        addonRepository.delete(id);
    }

    private void checkForDuplicateAddon(Addon addon) {
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
