package com.telerikacademy.addonis.models;

import com.telerikacademy.addonis.services.contracts.MailService;
import com.telerikacademy.addonis.services.contracts.UserService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private UserService service;

    private VerificationTokenService verificationTokenService;

    private MailService mailService;

//    private JavaMailSender mailSender;

    public RegistrationListener(VerificationTokenService verificationTokenService, MailService mailService) {
        this.verificationTokenService = verificationTokenService;
//        this.service = service;
        this.mailService = mailService;
//        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    public void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user, token);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setSubject("Registration Confirmation");
//        mailMessage.setText(token);
//        mailService.sendMail(user, mailMessage);

//        String recipientAddress = user.getEmail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl
//                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
////        String message = messages.getMessage("message.regSucc", null, event.getLocale());
//
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText("http://localhost:8080" + confirmationUrl);
//        mailSender.send(email);
    }
}
