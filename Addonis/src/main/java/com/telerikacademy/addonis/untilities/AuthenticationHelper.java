package com.telerikacademy.addonis.untilities;

import com.telerikacademy.addonis.exceptions.AuthenticationFailureException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Component
public class AuthenticationHelper {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHENTICATION_FAILURE_MESSAGE = "Wrong username or password.";

    private final UserService userService;

    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The requested resource requires authentication.");
        }

        try {
            String username = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            return userService.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username.");
        }
    }

    public User tryGetUser(HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser == null) {
            throw new AuthenticationFailureException("No user logged in.");
        }

        return userService.getByUsername(currentUser);
    }

    public User verifyAuthentication(String username, String password) {
        try {
            User user = userService.getByUsername(username);
            if (!user.getPassword().equals(password)) {
                throw new AuthenticationFailureException(AUTHENTICATION_FAILURE_MESSAGE);
            }
            return user;
        } catch (EntityNotFoundException e) {
            throw new AuthenticationFailureException(AUTHENTICATION_FAILURE_MESSAGE);
        }
    }

    public boolean isUserAnonymous(HttpSession session) {
        return session.getAttribute("currentUser") == null;
    }

    public boolean isUserLogged(HttpSession session) {
        return !isUserAnonymous(session);
    }
}

