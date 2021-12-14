package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.SearchDto;
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
public class UserMVC extends BaseMvcController{

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
        model.addAttribute("users",userService.getAll());
        return "users";
    }

    @GetMapping("/{id}/block")
    public String blockUser(@PathVariable int id, HttpSession session){
        User user = getLoggedUser(session);
        userService.block(id,user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/unblock")
    public String unblockUser(@PathVariable int id, HttpSession session){
        User user = getLoggedUser(session);
        userService.unblock(id,user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id, HttpSession session, Model model){
        User user = getLoggedUser(session);
        try {
            userService.delete(id, user);
        } catch (IllegalArgumentException e){
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
        return "redirect:/users/";
    }

    @GetMapping("/{id}/addons")
    public String showAllAddonsOnUserPage(@PathVariable int id ,Model model, HttpSession session) {
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


}
