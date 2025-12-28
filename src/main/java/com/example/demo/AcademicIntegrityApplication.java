package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AcademicIntegrityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicIntegrityApplication.class, args);
    }

    // Initialize Roles on startup so Registration works immediately
    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepo) {
        return args -> {
            if (roleRepo.count() == 0) {
                roleRepo.save(new Role("ADMIN"));
                roleRepo.save(new Role("FACULTY"));
                roleRepo.save(new Role("REVIEWER"));
            }
        };
    }
}