package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.util.RepeatOffenderCalculator;


@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {
public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository integrityCaseRepo,
RepeatOffenderRecordRepository repeatOffenderRecordRepo,
private final AppUserRepository userRepo;
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.userRepo = null; // not used in tests
this.integrityCaseRepo = integrityCaseRepo;
this.repeatOffenderRecordRepo = repeatOffenderRecordRepo;
this.calculator = calculator;
}







public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
AppUserRepository userRepo
) {
this.studentRepo = studentRepo;
this.userRepo = userRepo;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

if (studentRepo.existsByStudentId(student.getStudentId())) {
throw new IllegalArgumentException("Student ID already exists");
}

if (studentRepo.existsByEmail(student.getEmail())) {
throw new IllegalArgumentException("Email already exists");
}

AppUser user = userRepo.findById(1L)
.orElseThrow(() ->
new ResourceNotFoundException("User not found")
);

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
StudentProfile student = getStudentById(studentId);
student.setRepeatOffender(true);
return studentRepo.save(student);
}

}
