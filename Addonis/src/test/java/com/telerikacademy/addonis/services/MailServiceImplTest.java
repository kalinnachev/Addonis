package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.Helpers;
import com.telerikacademy.addonis.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
public class MailServiceImplTest {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    MailServiceImpl service;

    @Test
    public void sendMail_should_callJavaEmailSender(){
        User mockUser = Helpers.createMockUser();
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        service.sendMail(mockUser,mailMessage);

        Mockito.verify(javaMailSender, Mockito.times(1))
                .send(mailMessage);
    }
}
