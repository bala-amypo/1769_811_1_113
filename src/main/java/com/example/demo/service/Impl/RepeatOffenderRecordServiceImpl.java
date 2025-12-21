package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;

@Service
public class RepeatOffenderRecordServiceImpl
        implements RepeatOffenderRecordService {

    private final RepeatOffenderRecordRepository repo;
    private final StudentProfileRepository studentRepo;

    public RepeatOffenderRecordServiceImpl(
            RepeatOffenderRecordRepository repo,
            StudentProfileRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RepeatOffenderRecord refreshRepeatOffenderData(String studentId) {

        StudentProfile student = studentRepo
                .findByStudentId(studentId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "StudentProfile not found for studentId: " + studentId));

        RepeatOffenderRecord record =
                repo.findByStudentProfile_StudentId(studentId);

        if (record == null) {
            record = new RepeatOffenderRecord();
            record.setStudentProfile(student); // ✅ REQUIRED
            record.setCaseCount(0);
        }

        record.setRepeatOffender(record.getCaseCount() >= 2);
        record.setLastUpdated(LocalDateTime.now());

        return repo.save(record);
    }
}
