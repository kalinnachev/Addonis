package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.events.UserRegistrationCompleteEvent;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.LoginDto;
import com.telerikacademy.addonis.models.dto.RegisterDto;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import com.telerikacademy.addonis.untilities.IOUtils;
import com.telerikacademy.addonis.untilities.ModelMapperUser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseMvcController {
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapperUser modelMapperUser;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthenticationController(AuthenticationHelper authenticationHelper, ModelMapperUser modelMapperUser, UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        super(authenticationHelper);
        this.authenticationHelper = authenticationHelper;
        this.modelMapperUser = modelMapperUser;
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("login") LoginDto login,
                              BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        authenticationHelper.verifyAuthentication(login.getUsername(), login.getPassword());
        session.setAttribute("currentUser", login.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.removeAttribute("currentUser");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("register", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("register") RegisterDto register,
                                 BindingResult bindingResult,
                                 HttpSession session, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!register.getPassword().equals(register.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "password_error", "Password confirmation should match password.");
            return "register";
        }
        User user = modelMapperUser.fromDto(register);
        userService.create(user, IOUtils.convert(register.getMultipartFile()));
        applicationEventPublisher.publishEvent(new UserRegistrationCompleteEvent(user));
        return "redirect:/auth/login";
    }
}


