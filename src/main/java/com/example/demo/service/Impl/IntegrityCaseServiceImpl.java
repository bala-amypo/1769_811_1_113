package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

private final IntegrityCaseRepository caseRepo;
private final StudentProfileRepository studentRepo;

public IntegrityCaseServiceImpl(
IntegrityCaseRepository caseRepo,
StudentProfileRepository studentRepo){
this.caseRepo=caseRepo;
this.studentRepo=studentRepo;
}

public IntegrityCase createCase(IntegrityCase c){
if(c.getStudentProfile()==null)
throw new IllegalArgumentException("Student profile required");
return caseRepo.save(c);
}

public IntegrityCase updateCaseStatus(Long id,String status){
IntegrityCase c=getCaseById(id);
c.setStatus(status);
return caseRepo.save(c);
}

public List<IntegrityCase> getCasesByStudent(Long studentId){
return caseRepo.findByStudentProfile_Id(studentId);
}

public IntegrityCase getCaseById(Long id){
return caseRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Case not found"));
}
}
