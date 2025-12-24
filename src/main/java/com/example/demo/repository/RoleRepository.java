package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.entity.*;
public interface RoleRepository extends JpaRepository<Role,Long>{
Optional<Role> findByName(String name);
}
