package com.telerikacademy.addonis.contollers.mvc;

import com.telerikacademy.addonis.exceptions.*;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.models.dto.SearchDto;
import com.telerikacademy.addonis.models.dto.UserSearchDto;
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
    public String authenticationFailureErrorHandler(AuthenticationFailureException e) {
        return "redirect:/auth/login";
    }

    @ExceptionHandler({DuplicateEntityException.class, EntityNotFoundException.class,IllegalArgumentException.class ,StorageServiceException.class})
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

    @ModelAttribute("loggedUserID")
    public Integer loggedUseID(HttpSession session) {
        return authenticationHelper.isUserLogged(session)? getLoggedUser(session).getId() : null;
    }

    @ModelAttribute("isUserLogged")
    public boolean isUserLogged(HttpSession session) {
        return authenticationHelper.isUserLogged(session);
    }

    @ModelAttribute("isAdmin")
    public boolean isLoggedUserAdmin(HttpSession session) {
        try {
            return getLoggedUser(session).isAdmin();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

    @ModelAttribute("isBlocked")
    public boolean isLoggedUserBlocked(HttpSession session) {
        try {
            return getLoggedUser(session).isBlocked();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

    @ModelAttribute("isEnabled")
    public boolean isLoggedUserEnabled(HttpSession session) {
        try {
            return getLoggedUser(session).isEnabled();
        } catch (AuthenticationFailureException e) {
            return false;
        }
    }

    @ModelAttribute("vf")
    public ViewFormatter populateViewFormatterHelper() {
        return new ViewFormatter();
    }

    @ModelAttribute("search")
    public SearchDto searchBar() {
        return new SearchDto();
    }

    @ModelAttribute("userSearch")
    public UserSearchDto userSearchBar(){
        return new UserSearchDto();
    }


}
