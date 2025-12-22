package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;

public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase,Long> {

List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);
}
