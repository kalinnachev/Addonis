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
import java.util.stream.Collectors;

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
    public List<Addon> getByUser(Integer userID, Optional<User> loggedUser) {
        List<Addon> allUserAddons = addonRepository.getByUser(userID);
        // only admin or loggedUser can see pending addons
        if(loggedUser.isPresent()){
            if(loggedUser.get().isAdmin() || loggedUser.get().getId().equals(userID)){
                return allUserAddons;
            }
        }
        return allUserAddons.stream().filter(Addon::isApproved).collect(Collectors.toList());
    }

    @Override
    public void update(Addon addon) {
        addonRepository.update(addon);
    }

    @Override
    public List<Addon> getPending(User user) {
       checkIfAdmin(user);
       return addonRepository.getPending();
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
        throwIfNotAdminOrCreator(user, addon.getId());
        checkIfBlocked(user);

        binaryContent.ifPresent(file->setBinaryContent(addon,file));
        String originUrl = getById(addon.getId()).getOriginUrl();
        addonRepository.update(addon);
        updateGitInfo(addon, originUrl);
    }

    @Override
    public void delete(int id, User user) {
        throwIfNotAdminOrCreator(user, id);
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

    private void throwIfNotAdminOrCreator(User loggedUser, int addonId) {
        if(loggedUser.isAdmin())return;
        Addon addon = addonRepository.getById(addonId);
        if(!addon.getCreator().equals(loggedUser)){
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

    private void setBinaryContent(Addon addon, File binaryContent) {
        String fileName = fileService.storeBinaryContent(binaryContent, addon);
        addon.setBinaryContentUrl(fileName);
    }

    private void updateGitInfo(Addon addon, String originUrl) {
        if(!originUrl.equals(addon.getOriginUrl())){
            repoInfoService.updateInfoForAddon(addon);
        }
    }
}
