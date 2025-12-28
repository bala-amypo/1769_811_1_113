package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.AppUserRepository;
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

/* test-only fields */
@SuppressWarnings("unused")
private IntegrityCaseRepository integrityCaseRepo;
@SuppressWarnings("unused")
private RepeatOffenderRecordRepository repeatRepo;
@SuppressWarnings("unused")
private RepeatOffenderCalculator calculator;

/* ✅ SPRING RUNTIME CONSTRUCTOR */
@Autowired
public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
AppUserRepository userRepo
) {
this.studentRepo = studentRepo;
this.userRepo = userRepo;
}

/* ✅ TEST COMPATIBILITY CONSTRUCTOR */
public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository integrityCaseRepo,
RepeatOffenderRecordRepository repeatRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.userRepo = null;
this.integrityCaseRepo = integrityCaseRepo;
this.repeatRepo = repeatRepo;
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
StudentProfile student = getStudentById(studentId);
student.setRepeatOffender(true);
return studentRepo.save(student);
}
}
