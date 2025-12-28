package com.example.demo.service.impl;

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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository studentProfileRepository;
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator calculator;
private final AppUserRepository appUserRepository;

/* 🔴 REQUIRED constructor (exact order + ADD userRepo at end) */
public StudentProfileServiceImpl(
StudentProfileRepository studentProfileRepository,
IntegrityCaseRepository integrityCaseRepository,
RepeatOffenderRecordRepository repeatOffenderRecordRepository,
RepeatOffenderCalculator calculator,
AppUserRepository appUserRepository
) {
this.studentProfileRepository = studentProfileRepository;
this.integrityCaseRepository = integrityCaseRepository;
this.repeatOffenderRecordRepository = repeatOffenderRecordRepository;
this.calculator = calculator;
this.appUserRepository = appUserRepository;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

/* 🔐 Get logged-in user's email from JWT */
String email =
SecurityContextHolder
.getContext()
.getAuthentication()
.getName();

/* 🔍 Fetch AppUser */
AppUser user = appUserRepository.findByEmail(email)
.orElseThrow(() ->
new ResourceNotFoundException("User not found: " + email)
);

/* 🔧 Mandatory fields */
student.setId(null);
student.setUser(user);               // ✅ FIX: SET user_id
student.setRepeatOffender(false);

return studentProfileRepository.save(student);
}

@Override
public StudentProfile getStudentById(Long id) {
return studentProfileRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return studentProfileRepository.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student = getStudentById(studentId);

List<IntegrityCase> cases =
integrityCaseRepository.findByStudentProfile(student);

RepeatOffenderRecord record =
calculator.computeRepeatOffenderRecord(student, cases);

student.setRepeatOffender(record.getTotalCases() >= 2);

repeatOffenderRecordRepository
.findByStudentProfile(student)
.ifPresentOrElse(
r -> {
r.setTotalCases(record.getTotalCases());
r.setFlagSeverity(record.getFlagSeverity());
repeatOffenderRecordRepository.save(r);
},
() -> repeatOffenderRecordRepository.save(record)
);

return studentProfileRepository.save(student);
}
}
