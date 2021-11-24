package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

    String storeBinaryContent(MultipartFile file, Addon addon);

    String storeUserPicture(MultipartFile file, User user);

    byte[] getUserPicture(User user);

    byte[] getBinaryContent(Addon addon);

}
