package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.IntegrityCase;
import java.util.List;

public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase, Long> {

List<IntegrityCase> findByStudentProfile_Id(Long studentId);
}
