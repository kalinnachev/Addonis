package com.telerikacademy.addonis.events;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class UserRegistrationListener {

    private final VerificationTokenService verificationTokenService;
    private final MailService mailService;
    private final JavaMailSender emailSender;


    @Autowired
    public UserRegistrationListener(VerificationTokenService verificationTokenService, MailService mailService, JavaMailSender emailSender) {
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
        this.emailSender = emailSender;
    }

//    @Async
    @EventListener
    public void sendConformationEmail(UserRegistrationCompleteEvent event) throws MessagingException, UnsupportedEncodingException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .replacePath(null)
                .build()
                .toUriString();

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user, token);
        String object = "Please verify your registration";
        String senderName = "Addon Team";
        String mainContent = "<p>Dear" + user.getFirstName()+user.getLastName() +" ,</p>";
        mainContent += "<p> Please click the link below to verify to your registration:</p>";

        String verityURL = url + "/auth/verify?token=" + token;
        mainContent += "<h3><a =\"href=" + verityURL +"\" > VERIFY</a></h3>";
        mainContent += "<p>Thank you<br>Addon Team</p>";

        MimeMessage mailMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        helper.setFrom("addonis.application@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(object);
        helper.setText(mainContent,true);
//        emailSender.send(mailMessage);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setSubject("Addonis - Confirmation Email");
//        mailMessage.setText(token);
//        mailService.sendMail(user, mailMessage);

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
