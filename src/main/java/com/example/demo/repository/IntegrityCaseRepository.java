package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;

public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase, Long> {

/* REQUIRED BY TESTS */
List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

/* ALSO REQUIRED BY TESTS (String-based) */
@Query("""
select c from IntegrityCase c
where c.studentProfile.studentId = :studentIdentifier
""")
List<IntegrityCase> findByStudentIdentifier(
@Param("studentIdentifier") String studentIdentifier
);

/* REQUIRED */
List<IntegrityCase> findByStatus(String status);

@Query("""
select c from IntegrityCase c
where c.status = :status
and c.incidentDate >= :sinceDate
""")
List<IntegrityCase> findRecentCasesByStatus(
@Param("status") String status,
@Param("sinceDate") LocalDate sinceDate
);

List<IntegrityCase> findByIncidentDateBetween(
LocalDate start,
LocalDate end
);
}
