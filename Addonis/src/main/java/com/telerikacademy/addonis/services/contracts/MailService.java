package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface MailService {

    void sendMail(User recipient, SimpleMailMessage mailMessage);

    void sendMail(User recipient, String subject, String body);

}
