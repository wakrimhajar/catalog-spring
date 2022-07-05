package com.example.demo;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class CatalogApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);



    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Role role1 = new Role(null,"user");
            Role role2 = new Role(null,"admin");
            Role r1 = roleRepository.save(role1);
            Role r2 = roleRepository.save(role2);
            User user1 = new User(null,"user1","1234",List.of(r1));
            userRepository.save(user1);
            User user2 = new User(null,"user2","1234",List.of(r1));
            userRepository.save(user2);
            User user3 = new User(null,"admin","1234",List.of(r2));
            userRepository.save(user3);

        };
    }


}
