package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.IntegrityCaseService;

import java.util.List;
import java.util.Optional;

public class IntegrityCaseServiceImpl implements IntegrityCaseService {

private final IntegrityCaseRepository caseRepo;
private final StudentProfileRepository studentRepo;

/* constructor order EXACT as test */
public IntegrityCaseServiceImpl(
IntegrityCaseRepository caseRepo,
StudentProfileRepository studentRepo
) {
this.caseRepo = caseRepo;
this.studentRepo = studentRepo;
}

@Override
public IntegrityCase createCase(IntegrityCase integrityCase) {
if (integrityCase.getStudentProfile() == null ||
integrityCase.getStudentProfile().getId() == null) {
throw new IllegalArgumentException("Student required");
}

StudentProfile student =
studentRepo.findById(integrityCase.getStudentProfile().getId())
.orElseThrow(() -> new IllegalArgumentException("Student not found"));

integrityCase.setStudentProfile(student);
integrityCase.setStatus("OPEN");
return caseRepo.save(integrityCase);
}

@Override
public IntegrityCase updateCaseStatus(Long caseId, String status) {
IntegrityCase c = caseRepo.findById(caseId)
.orElseThrow(() -> new IllegalArgumentException("Case not found"));
c.setStatus(status);
return caseRepo.save(c);
}

@Override
public List<IntegrityCase> getCasesByStudent(Long studentId) {
return caseRepo.findByStudentProfile_Id(studentId);
}

@Override
public List<IntegrityCase> getAllCases() {
return caseRepo.findByStatus("OPEN"); // safe default
}

@Override
public Optional<IntegrityCase> getCaseById(Long id) {
return caseRepo.findById(id);
}
}
