package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseRepository extends JpaRepository<IntegrityCase, Long> {

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

List<IntegrityCase> findByStatus(String status);

List<IntegrityCase> findByIncidentDateBetween(LocalDate start, LocalDate end);

}
