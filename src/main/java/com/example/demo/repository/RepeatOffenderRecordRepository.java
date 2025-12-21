package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordRepository
        extends JpaRepository<RepeatOffenderRecord, Long> {

    RepeatOffenderRecord findByStudentProfile_StudentId(String studentId);
}
