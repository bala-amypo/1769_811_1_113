package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.service.RepeatOffenderCalculator;

@Service
@Transactional
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

private final StudentProfileRepository studentProfileRepository;
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator repeatOffenderCalculator;

public RepeatOffenderRecordServiceImpl(
StudentProfileRepository studentProfileRepository,
IntegrityCaseRepository integrityCaseRepository,
RepeatOffenderRecordRepository repeatOffenderRecordRepository,
RepeatOffenderCalculator repeatOffenderCalculator
) {
this.studentProfileRepository = studentProfileRepository;
this.integrityCaseRepository = integrityCaseRepository;
this.repeatOffenderRecordRepository = repeatOffenderRecordRepository;
this.repeatOffenderCalculator = repeatOffenderCalculator;
}

@Override
public RepeatOffenderRecord recalculate(Long studentProfileId) {

StudentProfile student =
studentProfileRepository.findById(studentProfileId)
.orElseThrow(() ->
new ResourceNotFoundException(
"StudentProfile not found with id: " + studentProfileId
)
);

List<IntegrityCase> cases =
integrityCaseRepository.findByStudentProfile_Id(studentProfileId);

RepeatOffenderRecord calculated =
repeatOffenderCalculator.calculate(student,cases);

RepeatOffenderRecord record =
repeatOffenderRecordRepository
.findByStudentProfile(student)
.orElse(calculated);

record.setTotalCases(calculated.getTotalCases());
record.setFlagSeverity(calculated.getFlagSeverity());
record.setFirstIncidentDate(calculated.getFirstIncidentDate());

repeatOffenderRecordRepository.save(record);

student.setRepeatOffender(calculated.getTotalCases() >= 2);

studentProfileRepository.save(student);

return record;
}

@Override
public RepeatOffenderRecord getByStudent(Long studentProfileId) {

StudentProfile student =
studentProfileRepository.findById(studentProfileId)
.orElseThrow(() ->
new ResourceNotFoundException(
"StudentProfile not found with id: " + studentProfileId
)
);

return repeatOffenderRecordRepository
.findByStudentProfile(student)
.orElseThrow(() ->
new ResourceNotFoundException(
"RepeatOffenderRecord not found for student id: " + studentProfileId
)
);
}
}
