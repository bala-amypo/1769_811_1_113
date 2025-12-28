package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository integrityCaseRepository,
RepeatOffenderRecordRepository repeatOffenderRecordRepository,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.integrityCaseRepository = integrityCaseRepository;
this.repeatOffenderRecordRepository = repeatOffenderRecordRepository;
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

StudentProfile student =
studentRepo.findById(studentId)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found")
);

boolean repeat =
calculator.isRepeatOffender(
integrityCaseRepository.findByStudentProfile(student)
);

student.setRepeatOffender(repeat);
return studentRepo.save(student);
}
}
