package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.models.User;
import org.springframework.context.ApplicationEvent;

public class UserRegistrationCompleteEvent extends ApplicationEvent {
    private final User user;

    public UserRegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
