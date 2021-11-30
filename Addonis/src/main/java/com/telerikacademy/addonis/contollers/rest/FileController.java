package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
https://blogs.perficient.com/2020/07/27/requestbody-and-multipart-on-spring-boot/
 */
@RestController
@RequestMapping("api/storage")
public class FileController {

    private final FileService fileService;

    private final UserService userService;

    private final AddonService addonService;

    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public FileController(FileService fileService, UserService userService, AddonService addonService, AuthenticationHelper authenticationHelper) {
        this.fileService = fileService;
        this.userService = userService;
        this.addonService = addonService;
        this.authenticationHelper = authenticationHelper;
    }

    @PutMapping ("/profile/picture")
    public User uploadFile( @RequestParam("picture") MultipartFile picture,
                           @RequestHeader HttpHeaders headers) throws IOException {
        User userToUpdate = authenticationHelper.tryGetUser(headers);
        File file = IOUtils.convert(picture);
        String fn = fileService.storeUserPicture(file, userToUpdate);
        userToUpdate.setPictureUrl(fn);
        userService.update(userToUpdate);
        return userToUpdate;
    }

    @GetMapping(value= "/profile/picture")
    public ResponseEntity<ByteArrayResource> getProfilePicture(@RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        byte[] data = fileService.getUserPicture(user);
        return generateResponse(user.getPictureUrl(), data);
    }

    @GetMapping(value= "/addon/content/{id}")
    public ResponseEntity<ByteArrayResource> getAddonBinary(@PathVariable int id) {
        Addon addon = addonService.getById(id);
        byte[] data = fileService.getBinaryContent(addon);
        return generateResponse(addon.getBinaryContentUrl(), data);
    }


    public ResponseEntity<ByteArrayResource> generateResponse(String fileName, byte[] data) {
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .contentType(contentType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "gif":
                return MediaType.IMAGE_GIF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
