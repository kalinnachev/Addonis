package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.models.Addon;

public class AddonApprovedEvent {

    private final Addon addon;
    private final String message;

    public AddonApprovedEvent(Addon addon, String message) {
        this.addon = addon;
        this.message = message;
    }

    public Addon getAddon() {
        return addon;
    }

    public String getMessage() {
        return message;
    }
}
