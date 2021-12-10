package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.exceptions.UnauthorizedFailureException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class AddonServiceImpl implements AddonService {

    public static final String ERROR_MSG_BLOCKED = "Action is blocked";
    public static final String ERROR_MSG_ADMIN = "User must be admin";
    public static final String ERROR_MSG_CREATOR = "You are not creator";

    private final AddonRepository addonRepository;
    private final RepoInfoService repoInfoService;
    private final FileService fileService;


    @Autowired
    public AddonServiceImpl(AddonRepository addonRepository, RepoInfoService repoInfoService, FileService fileService) {
        this.addonRepository = addonRepository;
        this.repoInfoService = repoInfoService;
        this.fileService = fileService;
    }

    @Override
    public Addon getById(int id) {
        return addonRepository.getById(id);
    }

    @Override
    public List<Addon> getByUser(Integer user) {
        return addonRepository.getByUser(user);
    }

    @Override
    public void update(Addon addon) {
        addonRepository.update(addon);
    }

    @Override
    public List<Addon> getAll() {
        return addonRepository.getAll();
    }

    @Override
    public void create(Addon addon, User user, File binaryContent) {
        checkIfBlocked(user);
        checkForDuplicateName(addon);
        checkForDuplicateOriginUrl(addon);

        repoInfoService.createInfoForAddon(addon);
        setBinaryContent(addon, binaryContent);
        addon.setNumberOfDownloads(0);
        addonRepository.create(addon);
    }

    @Override
    public void update(Addon addon, User user, Optional<File> binaryContent) {
        checkIfCreator(user, addon.getId());
        checkIfBlocked(user);

        binaryContent.ifPresent(file->setBinaryContent(addon,file));
        addonRepository.update(addon);
        repoInfoService.updateInfoForAddon(addon);
    }

    private void setBinaryContent(Addon addon, File binaryContent) {
        String fileName = fileService.storeBinaryContent(binaryContent, addon);
        addon.setBinaryContentUrl(fileName);
    }

    @Override
    public void delete(int id, User user) {
        checkIfCreator(user, id);
        checkIfBlocked(user);
        addonRepository.delete(id);
    }

    @Override
    public void approve(Addon addon, User user) {
        checkIfAdmin(user);
        addon.setApproved(true);
        addonRepository.update(addon);
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

    @Override
    public List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId, Optional<String> sort) {
        return addonRepository.filter(name,targetIdeId,sort);
    }



    private void checkIfAdmin(User user) {
        if(!user.isAdmin()){
            throw new UnauthorizedFailureException(ERROR_MSG_ADMIN);
        }
    }

    private void checkIfBlocked(User user) {
        if(user.isBlocked()){
            throw new UnauthorizedFailureException(ERROR_MSG_BLOCKED);
        }
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

    private void checkIfCreator(User user, int id) {
        Addon addon = addonRepository.getById(id);
        if(!addon.getCreator().equals(user)){
            throw new UnauthorizedFailureException(ERROR_MSG_CREATOR);
        }
    }

    private void checkForDuplicateName(Addon addon) {
        boolean duplicateExist = true;
        try {
            addonRepository.getByName(addon.getName());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }
        if(duplicateExist) {
            throw new DuplicateEntityException("Addon", "name", addon.getName());
        }
    }
}
