package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase,Long> {

List<IntegrityCase> findByStudentIdentifier(Long studentId);

List<IntegrityCase> findByStudentProfile_StudentId(String studentId);

List<IntegrityCase> findByStatus(String status);

List<IntegrityCase> findByIncidentDateBetween(
LocalDate start,
LocalDate end
);

@Query("""
select c from IntegrityCase c
where c.status = :status
and c.incidentDate >= :sinceDate
""")
List<IntegrityCase> findRecentCasesByStatus(
@Param("status") String status,
@Param("sinceDate") LocalDate sinceDate
);
}
