package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;

public RepeatOffenderRecordServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository recordRepo) {

this.studentRepo = studentRepo;
this.caseRepo = caseRepo;
this.recordRepo = recordRepo;
}

@Override
public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {

StudentProfile student = studentRepo.findById(studentId)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found"));

List<IntegrityCase> cases =
caseRepo.findByStudentProfile_Id(studentId);

int totalCases = cases.size();

if (totalCases <= 2) {
student.setRepeatOffender(false);
studentRepo.save(student);
throw new IllegalStateException("Repeat offender requires at least 2 cases");
}

String severity;
if (totalCases == 2) severity = "LOW";
else if (totalCases == 3) severity = "MEDIUM";
else severity = "HIGH";

IntegrityCase lastCase = cases.get(cases.size() - 1);

RepeatOffenderRecord record =
recordRepo.findByStudentProfile(student)
.orElse(new RepeatOffenderRecord());

record.setStudentProfile(student);
record.setTotalCases(totalCases);
record.setLastIncidentDate(lastCase.getIncidentDate());
record.setFlagSeverity(severity);

student.setRepeatOffender(true);
studentRepo.save(student);

return recordRepo.save(record);
}

@Override
public RepeatOffenderRecord getRecordByStudent(Long studentId) {

StudentProfile student = studentRepo.findById(studentId)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found"));

return recordRepo.findByStudentProfile(student)
.orElseThrow(() ->
new ResourceNotFoundException("Repeat offender record not found"));
}

@Override
public List<RepeatOffenderRecord> getAllRepeatOffenders() {
return recordRepo.findAll();
}
}
