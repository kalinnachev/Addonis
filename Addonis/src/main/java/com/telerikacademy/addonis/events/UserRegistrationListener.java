package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Component
public class UserRegistrationListener {

    private VerificationTokenService verificationTokenService;
    private final MailService mailService;

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
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Addonis - Confirmation Email");
        mailMessage.setText(token);
        mailService.sendMail(user, mailMessage);

//        String recipientAddress = user.getEmail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl
//                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
//
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText("http://localhost:8080" + confirmationUrl);
//        mailSender.send(email);
    }
}
