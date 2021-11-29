package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.RepoInfo;

public interface RepoInfoService {

    void createInfoForAddon(Addon addon);

    void updateInfoForAddon(Addon addon);

}
