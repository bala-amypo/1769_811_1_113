package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RepeatOffenderRecordService;

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
public RepeatOffenderRecord refreshRepeatOffender(StudentProfile studentProfile) {

if(studentProfile == null)
throw new IllegalArgumentException("student constraint violated");

List<IntegrityCase> cases =
caseRepo.findByStudentProfile(studentProfile);

int totalCases = cases.size();
String severity;

if(totalCases == 1) severity = "LOW";
else if(totalCases == 2) severity = "MEDIUM";
else if(totalCases >= 4) severity = "HIGH";
else severity = "LOW";

RepeatOffenderRecord record =
recordRepo.findByStudentProfile(studentProfile)
.orElse(new RepeatOffenderRecord());

record.setStudentProfile(studentProfile);
record.setTotalCases(totalCases);
record.setFlagSeverity(severity);

if(!cases.isEmpty())
record.setFirstIncidentDate(cases.get(0).getIncidentDate());

studentProfile.setRepeatOffender(totalCases >= 2);
studentRepo.save(studentProfile);

return recordRepo.save(record);
}

@Override
public RepeatOffenderRecord getByStudentProfile(StudentProfile studentProfile) {

return recordRepo.findByStudentProfile(studentProfile)
.orElseThrow(() ->
new IllegalArgumentException("status record not found"));
}

@Override
public List<RepeatOffenderRecord> getAll() {
return recordRepo.findAll();
}
}
