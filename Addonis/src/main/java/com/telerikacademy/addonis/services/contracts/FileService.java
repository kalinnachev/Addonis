package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileService {

    String storeBinaryContent(File file, Addon addon);

    String storeUserPicture(File file, User user);

    byte[] getUserPicture(User user);

    byte[] getBinaryContent(Addon addon);

}
