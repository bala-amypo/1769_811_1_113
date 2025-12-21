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

    public RepeatOffenderRecordServiceImpl(
            RepeatOffenderRecordRepository repo) {
        this.repo = repo;
    }

    public RepeatOffenderRecord refreshRepeatOffenderData(String studentId) {
        RepeatOffenderRecord r =
                repo.findByStudentProfile_StudentId(studentId);
        if (r == null) {
            r = new RepeatOffenderRecord();
        }
        return repo.save(r);
    }

    public RepeatOffenderRecord getRecordByStudent(String studentId) {
        return repo.findByStudentProfile_StudentId(studentId);
    }

    public List<RepeatOffenderRecord> getAllRepeatOffenders() {
        return repo.findAll();
    }
}
