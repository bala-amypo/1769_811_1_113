package com.example.demo.service.impl;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.util.RepeatOffenderCalculator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

private final StudentProfileRepository studentProfileRepository;
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator calculator;

/* 🔴 REQUIRED BY TEST (exact constructor) */
public RepeatOffenderRecordServiceImpl(
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
public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {
StudentProfile student = studentProfileRepository.findById(studentId)
.orElseThrow(() -> new RuntimeException("User not found"));

List<IntegrityCase> cases =
integrityCaseRepository.findByStudentProfile(student);

RepeatOffenderRecord record =
calculator.computeRepeatOffenderRecord(student, cases);

return repeatOffenderRecordRepository.save(record);
}

@Override
public Optional<RepeatOffenderRecord> getRecordByStudent(Long studentId) {
return studentProfileRepository.findById(studentId)
.flatMap(repeatOffenderRecordRepository::findByStudentProfile);
}

@Override
public List<RepeatOffenderRecord> getAllRepeatOffenders() {
return repeatOffenderRecordRepository.findAll();
}
}
