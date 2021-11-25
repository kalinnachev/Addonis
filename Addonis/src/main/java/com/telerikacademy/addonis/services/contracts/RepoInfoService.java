package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.RepoInfo;

public interface RepoInfoService {

    RepoInfo create(Addon addon);

    RepoInfo update(Addon addon);

}
