package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
    https://www.baeldung.com/spring-email
 */
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMail(User recipient, SimpleMailMessage mailMessage) {
        mailMessage.setTo(recipient.getEmail());
        mailMessage.setFrom("addonis.application@gmail.com");
        emailSender.send(mailMessage);
    }
}
