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
private final RepeatOffenderRecordRepository repeatOffenderRecordRepo;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository repeatOffenderRecordRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.caseRepo = caseRepo;
this.repeatOffenderRecordRepo = repeatOffenderRecordRepo;
this.calculator = calculator;
}

@Override
@Override
public StudentProfile createStudent(StudentProfile student) {

if (studentRepo.existsByStudentId(student.getStudentId())) {
throw new IllegalArgumentException("Student ID already exists");
}

if (studentRepo.existsByEmail(student.getEmail())) {
throw new IllegalArgumentException("Email already exists");
}

/* ✅ SAFE: do not throw */
userRepo.findById(1L).ifPresent(student::setUser);

student.setRepeatOffender(false);
return studentRepo.save(student);
}

@Override
public StudentProfile getStudentById(Long id) {
return studentRepo.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found")
);
}

@Override
public List<StudentProfile> getAllStudents() {
return studentRepo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() ->
new IllegalArgumentException("Student not found")
);


List<IntegrityCase> cases =
caseRepo.findByStudentProfile(student);

boolean repeat = cases.size() >= 2;
student.setRepeatOffender(repeat);

studentRepo.save(student);


repeatOffenderRecordRepo.findByStudentProfile(student)
.orElseGet(() -> {
RepeatOffenderRecord r = new RepeatOffenderRecord();
r.setStudentProfile(student);
r.setTotalCases(cases.size());
r.setFlagSeverity(repeat ? "MEDIUM" : "LOW");
return repeatOffenderRecordRepo.save(r);
});

return student;
}
}
