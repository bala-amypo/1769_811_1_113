package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.util.RepeatOffenderCalculator;

import com.example.demo.entity.AppUser;

import com.example.demo.repository.AppUserRepository;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final AppUserRepository userRepo;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
AppUserRepository userRepo
) {
this.studentRepo = studentRepo;
this.userRepo = userRepo;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

AppUser user = userRepo.findById(1L)
.orElseThrow(() -> new ResourceNotFoundException("User not found"));

student.setUser(user);

return studentRepo.save(student);
}



@Override
public StudentProfile getStudentById(Long id) {
return studentProfileRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
