package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

public static void main(String[] args) {
SpringApplication.run(DemoApplication.class, args);
}

/* ✅ ROLE INITIALIZATION (moved from AcademicIntegrityApplication) */
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
