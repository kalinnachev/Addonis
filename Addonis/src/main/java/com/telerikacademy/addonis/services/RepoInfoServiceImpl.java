package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import com.telerikacademy.addonis.repositories.contracts.GitHubRestApiConsumer;
import com.telerikacademy.addonis.repositories.contracts.RepoInfoRepository;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RepoInfoServiceImpl implements RepoInfoService {

    private final RepoInfoRepository repoInfoRepository;
    private final AddonRepository addonRepository;
    private final GitHubRestApiConsumer apiConsumer;
    private final Logger logger = LoggerFactory.getLogger(RepoInfoServiceImpl.class);

    @Autowired
    public RepoInfoServiceImpl(RepoInfoRepository repoInfoRepository, AddonRepository addonRepository, GitHubRestApiConsumer apiConsumer) {
        this.repoInfoRepository = repoInfoRepository;
        this.addonRepository = addonRepository;
        this.apiConsumer = apiConsumer;
    }

    @Override
    public RepoInfo create(Addon addon) {
        RepoInfo repoInfo = new RepoInfo();
        apiConsumer.populateRepoInfoFromApi(addon.getOriginUrl(), repoInfo);
        repoInfo.setLastUpdateDateTime(LocalDateTime.now());
        repoInfoRepository.create(repoInfo);
        return repoInfo;
    }

    @Override
    public RepoInfo update(Addon addon) {
        RepoInfo repoInfo = addon.getRepoInfo();
        apiConsumer.populateRepoInfoFromApi(addon.getOriginUrl(), repoInfo);
        repoInfo.setLastUpdateDateTime(LocalDateTime.now());
        repoInfoRepository.update(repoInfo);
        return repoInfo;
    }

    /*
     https://riptutorial.com/spring/example/21209/cron-expression
     */
    @Scheduled(cron = "0/30 * * * * *")
    private void updateAll(){
        logger.info("Scheduled refresh of all addon. Extracting data from GitHub");
        addonRepository.getAll().stream().forEach(this::update);
        logger.info("Scheduled refresh - DONE");

    }
}
