package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

public interface RepeatOffenderRecordRepository
extends JpaRepository<RepeatOffenderRecord, Long> {

Optional<RepeatOffenderRecord> findByStudentProfile(StudentProfile studentProfile);
}
