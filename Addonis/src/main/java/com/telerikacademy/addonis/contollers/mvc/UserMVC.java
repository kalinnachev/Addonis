package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/users")
public class UserMVC extends BaseMvcController{

    private final UserService userService;

    @Autowired
    public UserMVC(AuthenticationHelper authenticationHelper, UserService userService) {
        super(authenticationHelper);
        this.userService = userService;
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

}
