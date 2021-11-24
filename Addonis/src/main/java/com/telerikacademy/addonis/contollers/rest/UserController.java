package com.telerikacademy.addonis.contollers.rest;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.OnRegistrationCompleteEvent;
import com.telerikacademy.addonis.models.RegistrationListener;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserDto;
import com.telerikacademy.addonis.models.dto.UserUpdateDto;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.ModelMapperUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;
    private ModelMapperUser modelMapperUser;
//   private final ApplicationEventPublisher eventPublisher;
   private RegistrationListener registrationListener;

   @Autowired
    public UserController(UserService userService, ModelMapperUser modelMapperUser, RegistrationListener registrationListener) {
        this.userService = userService;
        this.modelMapperUser = modelMapperUser;
//        this.eventPublisher = eventPublisher;
       this.registrationListener = registrationListener;
   }

    @GetMapping
    public List<User> getAll(@RequestParam(required = false) Optional<String> username,
                             @RequestParam(required = false) Optional<String> email,
                             @RequestParam(required = false) Optional<String> phoneNumber) {
        return userService.search(username,email,phoneNumber);
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
    public User createUser(@Valid @RequestBody UserDto userDto,
                           HttpServletRequest request) {
        try {
            User user = modelMapperUser.fromDto(userDto);
            userService.crete(user);
            String appUrl = request.getContextPath();
            OnRegistrationCompleteEvent onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(user,
                    request.getLocale(), appUrl);
            registrationListener.confirmRegistration(onRegistrationCompleteEvent);
            return user;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        try {
            User user = modelMapperUser.fromDto(userUpdateDto, id);
            userService.update(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (DuplicateEntityException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    @PutMapping("/{id}/block")
    public User BlockUser(@PathVariable int id) {
        try {
           return userService.block(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/unblock")
    public User UnblockUser(@PathVariable int id) {
        try {
           return userService.unblock(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        try{
            userService.delete(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
