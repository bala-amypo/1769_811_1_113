package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
@Transactional
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

public RepeatOffenderRecordServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository recordRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.caseRepo = caseRepo;
this.recordRepo = recordRepo;
this.calculator = calculator;
}

@Override
public RepeatOffenderRecord recompute(Long studentId) {

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() ->
new IllegalArgumentException("Student not found: " + studentId)
);

List<IntegrityCase> cases =
caseRepo.findByStudentIdentifier(student.getStudentId());

RepeatOffenderRecord record =
calculator.computeRepeatOffenderRecord(student, cases);

return recordRepo.save(record);
}

@Override
public RepeatOffenderRecord getByStudent(Long studentId) {

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() ->
new IllegalArgumentException("Student not found: " + studentId)
);

return recordRepo.findByStudentProfile(student)
.orElseThrow(() ->
new IllegalArgumentException("RepeatOffenderRecord not found")
);
}
}
