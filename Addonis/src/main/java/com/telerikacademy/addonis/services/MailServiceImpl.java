package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/*
    https://www.baeldung.com/spring-email
 */
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    @Override
    public void sendMail(User recipient, SimpleMailMessage mailMessage) {
        mailMessage.setTo(recipient.getEmail());
        mailMessage.setFrom("noreply@addonis.com");
        emailSender.send(mailMessage);
    }

    @Async
    @Override
    public void sendMail(User recipient, String subject, String body){
        try {
            MimeMessage mailMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
            helper.setFrom("noreply@addonis.com", "Addonis Team");
            helper.setTo(recipient.getEmail());
            helper.setSubject(subject);
            helper.setText(body, true);
            emailSender.send(mailMessage);
        }catch (MessagingException | UnsupportedEncodingException e){
            logger.error(e.getMessage());
        }
    }
}
