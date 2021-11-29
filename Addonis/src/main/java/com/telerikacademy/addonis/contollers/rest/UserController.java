package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.events.UserRegistrationCompleteEvent;
import com.telerikacademy.addonis.events.UserRegistrationListener;
import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserDto;
import com.telerikacademy.addonis.models.dto.UserUpdateDto;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.ModelMapperUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public UserController(UserService userService, ModelMapperUser modelMapperUser,  AuthenticationHelper authenticationHelper, VerificationTokenService verificationTokenService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.modelMapperUser = modelMapperUser;
        this.authenticationHelper = authenticationHelper;
        this.verificationTokenService = verificationTokenService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/search")
    public List<User> getAll(@RequestParam(required = false) Optional<String> username,
                             @RequestParam(required = false) Optional<String> email,
                             @RequestParam(required = false) Optional<String> phoneNumber
            , @RequestHeader HttpHeaders headers) {
        User user = authenticationHelper.tryGetUser(headers);
        return userService.search(username, email, phoneNumber, user);
    }


    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
        //try {
        User user = modelMapperUser.fromDto(userDto);
        userService.create(user);
        String appUrl = request.getContextPath();
        applicationEventPublisher.publishEvent(new UserRegistrationCompleteEvent(user));

        return user;
//        } catch (DuplicateEntityException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        }
    }

    @PutMapping()
    public User updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto
            , @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            modelMapperUser.fromDto(userUpdateDto, user);
            userService.update(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (DuplicateEntityException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }


    @PutMapping("/{id}/block")
    public User BlockUser(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            return userService.block(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/unblock")
    public User UnblockUser(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            return userService.unblock(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            verificationTokenService.deleteVerificationToken(user);
            userService.delete(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
