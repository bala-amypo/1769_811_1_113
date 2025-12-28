package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

    private final RepeatOffenderRecordRepository recordRepo;
    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;

    public RepeatOffenderRecordServiceImpl(RepeatOffenderRecordRepository recordRepo, 
                                           StudentProfileRepository studentRepo,
                                           IntegrityCaseRepository caseRepo) {
        this.recordRepo = recordRepo;
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {
        StudentProfile student = studentRepo.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
        List<IntegrityCase> cases = caseRepo.findByStudentProfile(student);
        
        RepeatOffenderRecord record = recordRepo.findByStudentProfile(student)
            .orElse(new RepeatOffenderRecord());
        
        record.setStudentProfile(student);
        record.setTotalCases(cases.size());
        
        // Rule: Flag Severity
        if (cases.size() >= 4) record.setFlagSeverity("HIGH");
        else if (cases.size() >= 2) record.setFlagSeverity("MEDIUM");
        else record.setFlagSeverity("LOW");

        if (!cases.isEmpty()) {
            record.setLastIncidentDate(cases.get(cases.size() - 1).getIncidentDate());
        }
        
        return recordRepo.save(record);
    }

    @Override
    public Optional<RepeatOffenderRecord> getRecordByStudent(Long studentId) {
        return recordRepo.findByStudentProfile_Id(studentId);
    }

    @Override
    public List<RepeatOffenderRecord> getAllRepeatOffenders() {
        return recordRepo.findAll();
    }
}