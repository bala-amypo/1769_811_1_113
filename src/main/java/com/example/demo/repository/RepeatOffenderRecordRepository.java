package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.entity.*;


public interface RepeatOffenderRecordRepository extends JpaRepository<RepeatOffenderRecord,Long>{
Optional<RepeatOffenderRecord> findByStudentProfile(StudentProfile profile);
}

