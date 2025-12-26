package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
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
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));

List<IntegrityCase> cases =
caseRepo.findByStudentProfile(student);

student.setRepeatOffender(cases.size() >= 2);
studentRepo.save(student);

repeatRepo.findByStudentProfile(student)
.orElseGet(() -> {
RepeatOffenderRecord r =
calculator.computeRepeatOffenderRecord(student,cases);
return repeatRepo.save(r);
});

return student;
}

}
