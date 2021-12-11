package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.services.contracts.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class AddonListener {

    private static final String MAIL_BODY_WITH_ADMIN_MESSAGE =
            "Dear %s %s,\n" +
                    "This is to inform you that your addon %s was %s by our administrators.\n" +
                    "Message from our administrators: \n" +
                    "%s\n\n" +
                    "Thank you!\n" +
                    "Addonis team.";

    private static final String MAIL_BODY =
            "Dear %s %s,\n" +
                    "This is to inform you that your addon %s was %s by our administrators.\n" +
                    "Thank you!\n" +
                    "Addonis team.";

    private final MailService mailService;

    @Autowired
    public AddonListener(MailService mailService) {
        this.mailService = mailService;
    }

    @EventListener
    public void sendEmail(AddonApprovedEvent event) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Addonis - Approved " + event.getAddon().getName() + " addon");
        if (event.getMessage().isBlank()) {
            mailMessage.setText(String.format(MAIL_BODY,
                    event.getAddon().getCreator().getFirstName(),
                    event.getAddon().getCreator().getLastName(),
                    event.getAddon().getName(),
                    "approved"));
        } else {
            mailMessage.setText(String.format(MAIL_BODY_WITH_ADMIN_MESSAGE,
                    event.getAddon().getCreator().getFirstName(),
                    event.getAddon().getCreator().getLastName(),
                    event.getAddon().getName(),
                    "approved",
                    event.getMessage()));
        }
        mailService.sendMail(event.getAddon().getCreator(), mailMessage);
    }

    @EventListener
    public void sendEmail(AddonRejectedEvent event) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Addonis - Rejected " + event.getAddon().getName() + " addon");
        mailMessage.setText(String.format(MAIL_BODY_WITH_ADMIN_MESSAGE,
                event.getAddon().getCreator().getFirstName(),
                event.getAddon().getCreator().getLastName(),
                event.getAddon().getName(),
                "rejected",
                event.getMessage()));
        mailService.sendMail(event.getAddon().getCreator(), mailMessage);
    }
}
