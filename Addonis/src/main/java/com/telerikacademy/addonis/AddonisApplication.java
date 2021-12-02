package com.telerikacademy.addonis;

import com.telerikacademy.addonis.models.*;
import com.telerikacademy.addonis.repositories.contracts.TargetIdeRepository;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import com.telerikacademy.addonis.services.contracts.AddonService;
import com.telerikacademy.addonis.services.contracts.MailService;
import com.telerikacademy.addonis.services.contracts.RepoInfoService;
import com.telerikacademy.addonis.services.contracts.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
public class AddonisApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AddonisApplication.class, args);
    }

    @Override
    public void run(String... args) {


        //VerificationTokenService service = applicationContext.getBean(VerificationTokenService.class);

        //service.verificationToken("gg");
        //  test();
//
//
        //testToken();

        //applicationContext.getBean(FileService.class);



//        GitRepository gitRepository = applicationContext.getBean(GitRepository.class);
//        AddonGitInfo info =null;
//        for (int i = 0;i<10;i++) {
//            System.out.println("i = " + i);
//            info = gitRepository.getInfo("https://github.com/spring-projects/spring-boot");
//        }


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

    private void testToken() {
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        VerificationTokenRepository verificationTokenRepository = applicationContext.getBean(VerificationTokenRepository.class);

        User user = userRepository.getById(1);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken("TEST");
        verificationTokenRepository.create(verificationToken);


    }
    private void test(){

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        User user = userRepository.getById(1);
        TargetIdeRepository targetIdeRepository = applicationContext.getBean(TargetIdeRepository.class);
        TargetIde ide = targetIdeRepository.getById(1);




        Addon addon = new Addon();
        addon.setCreator(user);
        addon.setCreationDate(LocalDate.now());
        //addon.setApproved(false);
        addon.setTargetIde(ide);
        addon.setBinaryContentUrl("test");
        addon.setDescription("test");
        addon.setName("name");
        addon.setOriginUrl("https://github.com/spring-projects/spring-boot");

//        RepoInfoService repoInfoService = applicationContext.getBean(RepoInfoService.class);
//        RepoInfo info = repoInfoService.createInfoForAddon(addon);
//        addon.setRepoInfo(info);

//        AddonService addonService = applicationContext.getBean(AddonService.class);
//        addonService.create(addon);
    }

}
