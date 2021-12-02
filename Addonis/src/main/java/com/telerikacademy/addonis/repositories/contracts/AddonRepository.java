package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.Addon;
import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AddonRepository extends CRUDRepository<Addon> {
    Addon findByOriginUrl(String originUrl);

    List<Addon> getFeatured();

    List<Addon> getNewest();

    List<Addon> getPopular();

    Addon getByName(String name);

    List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId,
                       Optional<Integer> numberOfDownloads, Optional<LocalDate> uploadDate
            , Optional<LocalDate> lastCommitDate, Optional<String> sort);

































}
