package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentProfile;

@Repository
public interface StudentProfileRepository
extends JpaRepository<StudentProfile, Long> {

/* Find student using business identifier */
Optional<StudentProfile> findByStudentId(String studentId);

/* Find student using email */
Optional<StudentProfile> findByEmail(String email);

/* Check existence (used during registration) */
boolean existsByStudentId(String studentId);

boolean existsByEmail(String email);
}
