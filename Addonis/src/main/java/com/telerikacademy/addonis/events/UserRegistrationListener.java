package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Component
public class UserRegistrationListener {

    private final VerificationTokenService verificationTokenService;
    private final MailService mailService;

    @Autowired
    public UserRegistrationListener(VerificationTokenService verificationTokenService, MailService mailService) {
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
    }

    @EventListener
    public void sendConformationEmail(UserRegistrationCompleteEvent event) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .replacePath(null)
                .build()
                .toUriString();

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user, token);
        String verityURL = url + "/auth/verify?token=" + token;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<p>Dear ");
        stringBuilder.append(user.getFirstName() + " " + user.getLastName());
        stringBuilder.append(" ,</p>");

        stringBuilder.append("<p> Please click the link below to verify to your registration:</p>");
        stringBuilder.append("<h3><a href=\"" + verityURL + "\" > VERIFY</a></h3>");
        stringBuilder.append("<p>Thank you!<br>Addon Team</p>");

        mailService.sendMail(user, "", stringBuilder.toString());
    }
}
