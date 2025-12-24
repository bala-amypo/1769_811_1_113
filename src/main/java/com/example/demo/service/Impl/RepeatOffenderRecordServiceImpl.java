package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

@Service
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

public RepeatOffenderRecordServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository recordRepo,
RepeatOffenderCalculator calculator){
this.studentRepo=studentRepo;
this.caseRepo=caseRepo;
this.recordRepo=recordRepo;
this.calculator=calculator;
}

public RepeatOffenderRecord recalculate(Long studentId){
StudentProfile s=studentRepo.findById(studentId).orElseThrow();
int count=caseRepo.findByStudentProfile_Id(studentId).size();

RepeatOffenderRecord r=
recordRepo.findByStudentProfile(s).orElse(new RepeatOffenderRecord());

r.setStudentProfile(s);
r.setTotalCases(count);
r.setFlagSeverity(calculator.calculateSeverity(count));

s.setRepeatOffender(calculator.isRepeatOffender(count));
studentRepo.save(s);

return recordRepo.save(r);
}
}
