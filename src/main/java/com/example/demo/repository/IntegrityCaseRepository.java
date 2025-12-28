package com.example.demo.repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IntegrityCaseRepository {

Optional<IntegrityCase> findById(Long id);

IntegrityCase save(IntegrityCase integrityCase);

/* CRUD-related */
List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

/* HQL / HCQL methods (image + tests 65–70) */
List<IntegrityCase> findByStudentIdentifier(String studentId);

List<IntegrityCase> findRecentCasesByStatus(String status, LocalDate since);

List<IntegrityCase> findByIncidentDateBetween(LocalDate start, LocalDate end);

List<IntegrityCase> findByStatus(String status);
}
