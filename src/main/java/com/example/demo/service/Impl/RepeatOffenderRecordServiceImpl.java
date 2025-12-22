package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RepeatOffenderRecordService;

@Service
public class RepeatOffenderRecordServiceImpl
        implements RepeatOffenderRecordService {

    @Autowired
    private RepeatOffenderRecordRepository recordRepo;

    @Autowired
    private StudentProfileRepository studentRepo;

    @Override
    public RepeatOffenderRecord createOrUpdate(Long studentProfileId) {

        StudentProfile student =
                studentRepo.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        RepeatOffenderRecord record =
                recordRepo.findByStudentProfile(student)
                .orElse(new RepeatOffenderRecord());

        record.setStudentProfile(student);
        record.setCaseCount(record.getCaseCount() + 1);
        record.setRepeatOffender(record.getCaseCount() >= 2);

        return recordRepo.save(record);
    }

    @Override
    public RepeatOffenderRecord getById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    @Override
    public List<RepeatOffenderRecord> getAll() {
        return recordRepo.findAll();
    }
}
