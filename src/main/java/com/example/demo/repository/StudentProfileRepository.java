package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileRepository
extends JpaRepository<StudentProfile,Long> {

Optional<StudentProfile> findByStudentIdentifier(String studentId);

boolean existsByStudentId(String studentId);
}
