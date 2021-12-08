package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.exceptions.AuthenticationFailureException;
import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.exceptions.UnauthorizedFailureException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.untilities.AuthenticationHelper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class BaseMvcController {
    
    private final AuthenticationHelper authenticationHelper;

    public BaseMvcController(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
    }

    public User getLoggedUser(HttpSession session) {
        return authenticationHelper.tryGetUser(session);
    }

    @ExceptionHandler({AuthenticationFailureException.class})
    public String authenticationFailureErrorHandler() {
        return "redirect:/auth/login";
    }

    @ExceptionHandler({DuplicateEntityException.class, EntityNotFoundException.class})
    public ModelAndView entityErrorHandler(Exception exception) {
        return populateView("error", exception);
    }

    @ExceptionHandler({UnauthorizedFailureException.class})
    public ModelAndView accessDeniedErrorHandler(Exception exception) {
        return populateView("access-denied", exception);
    }

    private ModelAndView populateView(String view, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(view);
        modelAndView.addObject("error", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute("username")
    public String populateUsername(HttpSession session) {
        try {
            return getLoggedUser(session).getUsername();
        } catch (AuthenticationFailureException e) {
            return "";
        }
    }

    @ModelAttribute("isAdmin")
    public boolean populateIsEmployee(HttpSession session) {
        try {
            return getLoggedUser(session).isAdmin();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

    @ModelAttribute("isBlocked")
    public boolean populateIsBlocked(HttpSession session) {
        try {
            return getLoggedUser(session).isBlocked();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

    @ModelAttribute("isEnabled")
    public boolean populateIsEnabled(HttpSession session) {
        try {
            return getLoggedUser(session).isEnabled();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

}