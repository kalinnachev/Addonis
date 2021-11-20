package com.telerikacademy.addonis;

import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.RoleRepository;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AddonisApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AddonisApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello World!");

       UserRepository userRepository= applicationContext.getBean(UserRepository.class);
        System.out.println("userRepository = " + userRepository);
        User user = userRepository.findByUsername("admin");
        System.out.println("user = " + user);

        User toCreate = new User();
        toCreate.setUsername("password");
        toCreate.setPassword("password");
        toCreate.setFirstName("password");
        toCreate.setLastName("password");
        toCreate.setEmail("password");

        RoleRepository roleRepository= applicationContext.getBean(RoleRepository.class);
        toCreate.setRole(roleRepository.getUserRole());


        userRepository.create(toCreate);
    }

}
