package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RepeatOffenderRecordService;

@Service
public class RepeatOffenderRecordServiceImpl
        implements RepeatOffenderRecordService {

    private final RepeatOffenderRecordRepository recordRepo;
    private final StudentProfileRepository studentRepo;

    public RepeatOffenderRecordServiceImpl(
            RepeatOffenderRecordRepository recordRepo,
            StudentProfileRepository studentRepo) {

        this.recordRepo = recordRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RepeatOffenderRecord refreshRepeatOffenderData(String studentId) {

        StudentProfile student = studentRepo
                .findByStudentId(studentId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "StudentProfile not found with studentId: " + studentId));

        RepeatOffenderRecord record =
                recordRepo.findByStudentProfile_StudentId(studentId);

        if (record == null) {
            record = new RepeatOffenderRecord();
            record.setStudentProfile(student);
            record.setCaseCount(0);
        }

        record.setRepeatOffender(record.getCaseCount() >= 2);
        record.setLastUpdated(LocalDateTime.now());

        return recordRepo.save(record);
    }

    @Override
    public RepeatOffenderRecord getRecordByStudent(String studentId) {
        return recordRepo.findByStudentProfile_StudentId(studentId);
    }

    @Override
    public List<RepeatOffenderRecord> getAllRepeatOffenders() {
        return recordRepo.findAll();
    }
}
