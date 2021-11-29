package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.FileService;
import com.telerikacademy.addonis.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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

    @Autowired
    public FileController(FileService fileService, UserService userService, AddonService addonService) {
        this.fileService = fileService;
        this.userService = userService;
        this.addonService = addonService;
    }

    @PostMapping("/profile/picture/{id}")
    public String uploadFile(@PathVariable int id, @RequestParam("picture") MultipartFile picture) {
        User user = userService.getById(id);
       // String fn = fileService.storeUserPicture(picture, user);
      //  user.setPictureUrl(fn);
        userService.update(user);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("api/storage/profile/picture/"+id)
                .toUriString();

        return fileDownloadUri;
    }

    @GetMapping(value= "/profile/picture/{id}")
    public ResponseEntity<ByteArrayResource> getProfilePicture(@PathVariable int id) {
        User user = userService.getById(id);
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
