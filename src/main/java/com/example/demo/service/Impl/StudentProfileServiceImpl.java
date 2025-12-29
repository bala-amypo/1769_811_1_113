package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
@Transactional
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository repeatRepo;
private final AppUserRepository userRepo;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository repeatRepo,
AppUserRepository userRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.caseRepo = caseRepo;
this.repeatRepo = repeatRepo;
this.userRepo = userRepo;
this.calculator = calculator;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

if(student.getUser() == null || student.getUser().getId() == null)
throw new RuntimeException("User ID is required");

AppUser user = userRepo.findById(student.getUser().getId())
.orElseThrow(() -> new RuntimeException("User not found"));

student.setUser(user);
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

StudentProfile student = getStudentById(studentId);

List<IntegrityCase> cases =
caseRepo.findByStudentProfile(student);

int totalCases = cases.size();

RepeatOffenderRecord record =
repeatRepo.findByStudentProfile(student)
.orElseGet(() -> {
RepeatOffenderRecord r = new RepeatOffenderRecord();
r.setStudentProfile(student);   // ✅ CRITICAL
r.setStudentId(student.getStudentId());
return r;
});

record.setTotalCases(totalCases);
record.setLastIncidentDate(LocalDate.now());
record.setFlagSeverity(calculator.calculateSeverity(totalCases));

repeatRepo.save(record);

student.setRepeatOffender(totalCases >= 2);

return studentRepo.save(student);
}
}
