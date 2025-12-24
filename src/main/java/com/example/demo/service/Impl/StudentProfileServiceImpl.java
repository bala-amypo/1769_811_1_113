package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository recordRepo,
RepeatOffenderCalculator calculator){
this.studentRepo=studentRepo;
this.caseRepo=caseRepo;
this.recordRepo=recordRepo;
this.calculator=calculator;
}

public StudentProfile createStudent(StudentProfile s){
s.setRepeatOffender(false);
return studentRepo.save(s);
}

public StudentProfile getStudentById(Long id){
return studentRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
}

public List<StudentProfile> getAllStudents(){
return studentRepo.findAll();
}

public StudentProfile updateRepeatOffenderStatus(Long studentId){
StudentProfile profile=getStudentById(studentId);
int totalCases=caseRepo.findByStudentProfile_Id(studentId).size();

String severity=calculator.calculateSeverity(totalCases);
boolean repeat=calculator.isRepeatOffender(totalCases);

RepeatOffenderRecord record=
recordRepo.findByStudentProfile(profile).orElse(new RepeatOffenderRecord());

record.setStudentProfile(profile);
record.setTotalCases(totalCases);
record.setFlagSeverity(severity);
recordRepo.save(record);

profile.setRepeatOffender(repeat);
return studentRepo.save(profile);
}
}
