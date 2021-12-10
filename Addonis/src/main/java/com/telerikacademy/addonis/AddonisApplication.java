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
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class AddonisApplication{

    public static void main(String[] args) {
        SpringApplication.run(AddonisApplication.class, args);
    }


}
