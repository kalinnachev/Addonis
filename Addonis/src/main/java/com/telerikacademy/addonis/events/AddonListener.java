package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.services.contracts.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AddonListener {

    private final MailService mailService;

    @Autowired
    public AddonListener(MailService mailService) {
        this.mailService = mailService;
    }

    @Async
    @EventListener
    public void sendEmail(AddonApprovedEvent event) {
        System.out.println("Addon was approved " + event.getMessage());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Addonis - Approved " + event.getAddon().getName() +" addon");
        mailMessage.setText("" +
                "This is to inform you that your addon was approved");
        mailService.sendMail(event.getAddon().getCreator(), mailMessage);
    }

    @Async
    @EventListener
    public void sendEmail(AddonRejectedEvent event) {
        System.out.println("Addon was rejected " + event.getMessage());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Addonis - Rejected " + event.getAddon().getName() +" addon");
        mailMessage.setText("" +
                "This is to inform you that, unfortunately, your addon was rejected by our administrators.");
        mailService.sendMail(event.getAddon().getCreator(), mailMessage);
    }
}
