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
import com.example.demo.service.StudentProfileService;
import com.example.demo.service.RepeatOffenderCalculator;

@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository studentProfileRepository;
private final IntegrityCaseRepository integrityCaseRepository;
private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
private final RepeatOffenderCalculator repeatOffenderCalculator;

public StudentProfileServiceImpl(
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
public StudentProfile createStudent(StudentProfile studentProfile) {
studentProfile.setRepeatOffender(false);
return studentProfileRepository.save(studentProfile);
}

@Override
public StudentProfile getStudentById(Long id) {
return studentProfileRepository.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException("StudentProfile not found with id: " + id)
);
}

@Override
public List<StudentProfile> getAllStudents() {
return studentProfileRepository.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long id) {

StudentProfile student =
studentProfileRepository.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException(
"StudentProfile not found with id: " + id
)
);

List<IntegrityCase> cases =
integrityCaseRepository.findByStudentProfile_Id(
student.getId()
);

RepeatOffenderRecord calculated =
repeatOffenderCalculator.computeRepeatOffenderRecord(
student,
cases
);

RepeatOffenderRecord record =
repeatOffenderRecordRepository
.findByStudentProfile(student)
.orElse(calculated);

record.setTotalCases(calculated.getTotalCases());
record.setFlagSeverity(calculated.getFlagSeverity());
record.setFirstIncidentDate(calculated.getFirstIncidentDate());

repeatOffenderRecordRepository.save(record);

student.setRepeatOffender(calculated.getTotalCases() >= 2);

return studentProfileRepository.save(student);
}

}
