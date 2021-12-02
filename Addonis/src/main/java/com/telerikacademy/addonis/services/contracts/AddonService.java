package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import org.joda.time.LocalDate;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AddonService {
    Addon getById(int id);

    List<Addon> getAll();

    void create(Addon addon, User user, File binaryContent);

    void update(Addon addon, User user, Optional<File> binaryContent);

    void delete(int id, User user);

    void approve(Addon addon, User user);

    List<Addon> getFeatured();

    List<Addon> getNewest();

    List<Addon> getPopular();

    List<Addon> filter(Optional<String> name, Optional<Integer> targetIdeId, Optional<Integer> numberOfDownloads,
                       Optional<LocalDate> uploadDate, Optional<LocalDate> lastCommitDate, Optional<String> sort);


}
