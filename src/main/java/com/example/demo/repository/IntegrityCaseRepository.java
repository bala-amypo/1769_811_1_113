package com.example.demo.repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase, Long> {

List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

/* 🔴 FIXED: explicit JPQL */
@Query("""
select c
from IntegrityCase c
where c.studentProfile.studentId = :studentId
""")
List<IntegrityCase> findByStudentIdentifier(
@Param("studentId") String studentId
);

@Query("""
select c
from IntegrityCase c
where c.status = :status
and c.incidentDate >= :since
""")
List<IntegrityCase> findRecentCasesByStatus(
@Param("status") String status,
@Param("since") LocalDate since
);

List<IntegrityCase> findByIncidentDateBetween(
LocalDate start,
LocalDate end
);

List<IntegrityCase> findByStatus(String status);
}
