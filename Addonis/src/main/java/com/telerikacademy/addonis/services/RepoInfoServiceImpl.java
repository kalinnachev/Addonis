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
import java.util.List;

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
    public void createInfoForAddon(Addon addon) {
        RepoInfo repoInfo = new RepoInfo();
        extractedDataFromExternalSource(addon.getOriginUrl(), repoInfo);
        addon.setRepoInfo(repoInfo);
        repoInfoRepository.create(repoInfo);
    }

    @Override
    public void updateInfoForAddon(Addon addon) {
        RepoInfo repoInfo = addon.getRepoInfo();
        extractedDataFromExternalSource(addon.getOriginUrl(), repoInfo);
        repoInfoRepository.update(repoInfo);
    }

    private void extractedDataFromExternalSource(String url, RepoInfo repoInfo) {
        apiConsumer.populateRepoInfoFromApi(url , repoInfo);
        repoInfo.setLastUpdateDateTime(LocalDateTime.now());
    }

    /*
     https://riptutorial.com/spring/example/21209/cron-expression
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduledUpdate(){
        List<Addon> addonList =  addonRepository.getAll();
        logger.info("Scheduled update of all repos. Extracting data from GitHub...");
        for(Addon addon: addonList){
            logger.info("Extracting data from GitHub for addon {}", addon.getOriginUrl());
            extractedDataFromExternalSource(addon.getOriginUrl(), addon.getRepoInfo());
            repoInfoRepository.update(addon.getRepoInfo());
        }
        logger.info("Scheduled update of all repos - DONE");

    }
}
