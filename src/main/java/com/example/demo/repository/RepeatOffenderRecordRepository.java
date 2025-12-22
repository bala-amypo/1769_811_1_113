package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

@Repository
public interface RepeatOffenderRecordRepository
        extends JpaRepository<RepeatOffenderRecord, Long> {

    Optional<RepeatOffenderRecord> findByStudentProfile(StudentProfile studentProfile);
}
