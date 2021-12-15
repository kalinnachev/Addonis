package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserSearchDto;
import com.telerikacademy.addonis.models.dto.UserUpdatePassword;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.models.dto.UserUpdateDto;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/users")
public class UserMVC extends BaseMvcController {

    private final UserService userService;
    private final AddonService addonService;
    private final ModelMapperUser modelMapperUser;
    private final TargetIdeService targetIdeService;

    public UserMVC(AuthenticationHelper authenticationHelper, UserService userService, AddonService addonService, ModelMapperUser modelMapperUser, TargetIdeService targetIdeService) {
        super(authenticationHelper);
        this.userService = userService;
        this.addonService = addonService;
        this.modelMapperUser = modelMapperUser;
        this.targetIdeService = targetIdeService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/{id}/block")
    public String blockUser(@PathVariable int id, HttpSession session) {
        User user = getLoggedUser(session);
        userService.block(id, user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/unblock")
    public String unblockUser(@PathVariable int id, HttpSession session) {
        User user = getLoggedUser(session);
        userService.unblock(id, user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id, HttpSession session, Model model) {
        User user = getLoggedUser(session);
        userService.delete(id, user);

        return "redirect:/users/";
    }

    @GetMapping("/{id}/addons")
    public String showUserAddons(@PathVariable int id, Model model, HttpSession session) {
        Optional<User> loggedUser = isUserLogged(session)? Optional.of(getLoggedUser(session)) : Optional.empty();
        User addonsUser = userService.getById(id);
        model.addAttribute("addonlist", addonService.getByUser(id, loggedUser));
        model.addAttribute("user", addonsUser);
        return "myaddons";

    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("userSearch") UserSearchDto userSearchDto, HttpSession session) {
        User user = getLoggedUser(session);
        List<User> usersList = extractSearchResult(userSearchDto, user);
        model.addAttribute("users", usersList);

        model.addAttribute("title", getSearchTitle(usersList.size()));
        return "user-search";
    }

    @ModelAttribute("allTargetIde")
    public List<TargetIde> populateTargetIdes() {
        return targetIdeService.getAll();
    }

    private String getSearchTitle(int size) {
        if (size == 1)
            return "Search results (1 user):";
        if (size == 0)
            return "No users found";
        return "Search results (" + size + " users):";
    }

    private List<User> extractSearchResult(UserSearchDto userSearchDto, User user) {
        switch (userSearchDto.getSearchBy()) {
            case 1:
                return userService.search(
                        Optional.ofNullable(userSearchDto.getParam()),
                        Optional.empty(),
                        Optional.empty(),
                        user
                );
            case 2:
                return userService.search(
                        Optional.empty(),
                        Optional.ofNullable(userSearchDto.getParam()),
                        Optional.empty(),
                        user
                );
            case 3:
                return userService.search(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.ofNullable(userSearchDto.getParam()),
                        user
                );
            default:
                return userService.getAll();
        }
    }
    @GetMapping("/update")
    public String showUpdatePage(Model model, HttpSession session) {
        User user = getLoggedUser(session);
        UserUpdateDto userUpdateDto = modelMapperUser.toDto(user);
        model.addAttribute("userDto", userUpdateDto);
        return "user-update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("userDto") UserUpdateDto userUpdateDto,Model model,
                                 BindingResult errors,
                                 HttpSession session) throws IOException {
        if (errors.hasErrors()) {
            return "user-update";
        }

        User user = getLoggedUser(session);
        User userToUpdate = modelMapperUser.fromDto(userUpdateDto,user);
        File file = null;
        if (!userUpdateDto.getMultipartFile().isEmpty()) {
            file = IOUtils.convert(userUpdateDto.getMultipartFile());
        }
        userService.update(userToUpdate, Optional.ofNullable(file));
        return "redirect:/addons";
    }

    @GetMapping("/change/password")
    public String showChangePasswordPage(Model model, HttpSession session) {
        User user = getLoggedUser(session);
        UserUpdatePassword userUpdatePassword = new UserUpdatePassword();
        model.addAttribute("userDto", userUpdatePassword);
        return "user-update-password";
    }

    @PostMapping("/change/password")
    public String updateUserPassword(@Valid @ModelAttribute("userDto") UserUpdatePassword userUpdatePassword,
                             BindingResult errors,
                             HttpSession session)  {
        if (errors.hasErrors()) {
            return "user-update-password";
        }

        User user = getLoggedUser(session);
        if(!user.getPassword().equals(userUpdatePassword.getOldPassword())){
            errors.rejectValue("oldPassword", "password_error", "Invalid password");
            return "user-update-password";
        }
        if (!userUpdatePassword.getNewPassword().equals(userUpdatePassword.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "password", "Passwords should match");
            return "user-update-password";
        }
        user.setPassword(userUpdatePassword.getNewPassword());
        userService.update(user, Optional.empty());
        return "redirect:/addons";
    }
}
