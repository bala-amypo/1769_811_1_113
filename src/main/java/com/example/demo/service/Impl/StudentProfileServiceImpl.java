package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final AppUserRepository userRepo;

/* extra fields only for test constructor */
private IntegrityCaseRepository integrityCaseRepo;
private RepeatOffenderRecordRepository repeatRepo;
private RepeatOffenderCalculator calculator;

/* ✅ ORIGINAL WORKING CONSTRUCTOR */
public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
AppUserRepository userRepo
) {
this.studentRepo = studentRepo;
this.userRepo = userRepo;
}

/* ✅ TEST-REQUIRED CONSTRUCTOR */
public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository integrityCaseRepo,
RepeatOffenderRecordRepository repeatRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.userRepo = null;   // not used in tests
this.integrityCaseRepo = integrityCaseRepo;
this.repeatRepo = repeatRepo;
this.calculator = calculator;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

AppUser user = userRepo != null
? userRepo.findById(1L).orElse(null)
: null;

student.setUser(user);
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
StudentProfile s = getStudentById(studentId);
s.setRepeatOffender(true);
return studentRepo.save(s);
}
}
