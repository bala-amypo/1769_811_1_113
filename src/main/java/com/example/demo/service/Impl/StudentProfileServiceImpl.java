package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
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
public StudentProfile createStudent(StudentProfile student) {
student.setRepeatOffender(false);
return studentRepo.save(student);
}

@Override
public StudentProfile getStudentById(Long id) {
return studentRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return studentRepo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));

List<IntegrityCase> cases =
caseRepo.findByStudentProfile(student);

RepeatOffenderRecord record =
calculator.computeRepeatOffenderRecord(student, cases);

student.setRepeatOffender(record.getTotalCases() >= 2);

studentRepo.save(student);

recordRepo.findByStudentProfile(student)
.orElseGet(() -> recordRepo.save(record));

return student;
}
}
