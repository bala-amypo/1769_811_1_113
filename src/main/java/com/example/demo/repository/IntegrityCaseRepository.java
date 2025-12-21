package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseRepository
        extends JpaRepository<IntegrityCase, Long> {

    List<IntegrityCase> findByStudentProfile_StudentId(String studentId);
}
