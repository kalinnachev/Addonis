package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.UserSearchDto;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserMVC extends BaseMvcController {

    private final UserService userService;
    private final AddonService addonService;

    @Autowired
    public UserMVC(AuthenticationHelper authenticationHelper,
                   UserService userService,
                   AddonService addonService) {
        super(authenticationHelper);
        this.userService = userService;
        this.addonService = addonService;
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
    public String showAllAddonsOnUserPage(@PathVariable int id, Model model, HttpSession session) {
        try {
            User user = getLoggedUser(session);
            User addonsUser = userService.getById(id);
            model.addAttribute("addonlist", addonService.getByUser(id));
            model.addAttribute("user", addonsUser);
            return "myaddons";
        } catch (EntityNotFoundException e) {
            return "not_found";
        }
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("userSearch") UserSearchDto userSearchDto, HttpSession session) {
        User user = getLoggedUser(session);
        List<User> usersList = extractSearchResult(userSearchDto, user);
        model.addAttribute("users", usersList);

        model.addAttribute("title", getSearchTitle(usersList.size()));
        return "user-search";
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
}
