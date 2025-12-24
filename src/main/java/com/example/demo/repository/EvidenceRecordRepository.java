package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.entity.*;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{
Optional<AppUser> findByEmail(String email);
boolean existsByEmail(String email);
}
