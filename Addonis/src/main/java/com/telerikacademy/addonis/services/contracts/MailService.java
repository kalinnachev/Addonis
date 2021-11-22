package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.User;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface MailService {

    void sendMail(User recipient, SimpleMailMessage mailMessage);

    default void sendMail(List<User> recipientList, SimpleMailMessage mailMessage){
        recipientList.forEach(user->sendMail(user,mailMessage));
    }
}
