package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;

public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase,Long> {

/* REQUIRED BY TESTS */
List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

/* OPTIONAL */
List<IntegrityCase> findByStatus(String status);

List<IntegrityCase> findByIncidentDateBetween(
LocalDate start,
LocalDate end
);
}
