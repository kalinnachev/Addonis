package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.events.UserRegistrationCompleteEvent;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserDto;
import com.telerikacademy.addonis.models.dto.UserUpdateDto;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserService userService;
    private final ModelMapperUser modelMapperUser;
    private final AuthenticationHelper authenticationHelper;
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public UserController(UserService userService, ModelMapperUser modelMapperUser, AuthenticationHelper authenticationHelper, VerificationTokenService verificationTokenService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.modelMapperUser = modelMapperUser;
        this.authenticationHelper = authenticationHelper;
        this.verificationTokenService = verificationTokenService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @ApiOperation(value = "Get all users")
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @ApiOperation(value = "Search user by  username, email or phone number")
    @GetMapping("/search")
    public List<User> getAll(@RequestParam(required = false) Optional<String> username,
                             @RequestParam(required = false) Optional<String> email,
                             @RequestParam(required = false) Optional<String> phoneNumber
            , @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        return userService.search(username, email, phoneNumber, user);
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "Create new user")
    @PostMapping
    public User createUser(@Valid @RequestPart("user") UserDto userDto,
                           @RequestPart("picture") MultipartFile picture) throws IOException {
        User user = modelMapperUser.fromDto(userDto);
        userService.create(user, IOUtils.convert(picture));
        applicationEventPublisher.publishEvent(new UserRegistrationCompleteEvent(user));
        return user;
    }

    @ApiOperation(value = "Update existing user")
    @PutMapping()
    public User updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto
            , @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        modelMapperUser.fromDto(userUpdateDto, user);
        userService.update(user, Optional.empty());
        return user;
    }

    @ApiOperation(value = "Block existing user with the given id")
    @PutMapping("/{id}/block")
    public User blockUser(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        return userService.block(id, user);
    }

    @ApiOperation(value = "Unblock existing user with the given id")
    @PutMapping("/{id}/unblock")
    public User unblockUser(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        return userService.unblock(id, user);
    }

    @ApiOperation(value = "Delete user with the give id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        verificationTokenService.deleteVerificationToken(userService.getById(id));
        userService.delete(id, user);
    }
}
