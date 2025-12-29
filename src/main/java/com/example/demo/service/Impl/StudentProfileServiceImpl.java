package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AppUser;
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
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository studentProfileRepository;
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator calculator;

public StudentProfileServiceImpl(
StudentProfileRepository studentProfileRepository,
IntegrityCaseRepository integrityCaseRepository,
RepeatOffenderRecordRepository repeatOffenderRecordRepository,
RepeatOffenderCalculator calculator
) {
this.studentProfileRepository = studentProfileRepository;
this.integrityCaseRepository = integrityCaseRepository;
this.repeatOffenderRecordRepository = repeatOffenderRecordRepository;
this.calculator = calculator;
}

@Override
public StudentProfile createStudent(StudentProfile student) {

student.setRepeatOffender(false);


AppUser user = new AppUser();
user.setId(1L);  
student.setUser(user);

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
@Transactional
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student = studentRepo.findById(studentId)
.orElseThrow(() -> new RuntimeException("Student not found"));

int totalCases = caseRepo.countByStudentProfile(student);

RepeatOffenderRecord record = new RepeatOffenderRecord();
record.setStudentProfile(student);   // ✅ THIS WAS MISSING
record.setStudentId(student.getStudentId());
record.setTotalCases(totalCases);
record.setLastIncidentDate(LocalDate.now());
record.setFlagSeverity(calculator.calculateSeverity(totalCases));

repeatOffenderRecordRepo.save(record);  // ✅ now FK is inserted

student.setRepeatOffender(totalCases >= 3);

return studentRepo.save(student);
}

}
