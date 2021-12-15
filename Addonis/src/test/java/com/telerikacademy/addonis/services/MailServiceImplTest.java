package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static com.telerikacademy.addonis.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class MailServiceImplTest {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    MailServiceImpl service;

    @Captor
    ArgumentCaptor<MimeMessage> captor;

    @Test
    public void sendMail1_should_callJavaEmailSender() {
        User mockUser = createMockUser();
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        service.sendMail(mockUser, mailMessage);

        Mockito.verify(javaMailSender, Mockito.times(1))
                .send(mailMessage);
    }

    @Test
    public void sendMail2_should_callJavaEmailSender() {
        User mockUser = createMockUser();
        MimeMessage mockMessage = new MimeMessage((Session) null);

        Mockito.when(javaMailSender.createMimeMessage())
                .thenReturn(mockMessage);

        service.sendMail(mockUser, "subject", "body");
        Mockito.verify(javaMailSender).send(captor.capture());
        MimeMessage message = captor.getValue();

        Mockito.verify(javaMailSender, Mockito.times(1))
                .send(message);
    }
}
