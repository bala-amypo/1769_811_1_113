package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.StudentProfileService;
import com.example.demo.util.RepeatOffenderCalculator;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

/* constructor order EXACT as test */
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
.orElseThrow(() -> new ResourceNotFoundException("User not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return studentRepo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long studentId) {
StudentProfile student = getStudentById(studentId);
List<IntegrityCase> cases = caseRepo.findByStudentProfile(student);

RepeatOffenderRecord record =
calculator.computeRepeatOffenderRecord(student, cases);

recordRepo.save(record);
student.setRepeatOffender(record.getTotalCases() >= 2);

return studentRepo.save(student);
}
}
