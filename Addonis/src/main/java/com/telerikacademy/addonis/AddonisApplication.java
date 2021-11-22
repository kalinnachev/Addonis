package com.telerikacademy.addonis;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.services.contracts.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
public class AddonisApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AddonisApplication.class, args);
    }

    @Override
    public void run(String... args) {

        //testSendMail();



        //       GitRepository gitRepository = applicationContext.getBean(GitRepository.class);
 //       AddonGitInfo info =null;
//        for (int i = 0;i<10;i++) {
//            System.out.println("i = " + i);
 //           info = gitRepository.getInfo("https://github.com/VsVim/VsVim");
//        }
//

    }

    private void testSendMail() {
        MailService mailService = applicationContext.getBean(MailService.class);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Hello from Spring Boot");
        mailMessage.setText("This is the message body");

        User user = new User();
        user.setEmail("kalin.nachev@gmail.com");

        mailService.sendMail(user, mailMessage);

    }

}
