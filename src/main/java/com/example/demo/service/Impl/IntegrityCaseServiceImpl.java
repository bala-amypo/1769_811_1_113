package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository caseRepo;
    private final StudentProfileRepository studentRepo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository caseRepo, StudentProfileRepository studentRepo) {
        this.caseRepo = caseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase c) {
        if(c.getStudentProfile() != null && c.getStudentProfile().getId() != null) {
             // Validate student exists
             studentRepo.findById(c.getStudentProfile().getId())
                 .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        }
        return caseRepo.save(c);
    }

    @Override
    public IntegrityCase updateCaseStatus(Long caseId, String status) {
        IntegrityCase c = caseRepo.findById(caseId).orElseThrow(() -> new ResourceNotFoundException("Case not found"));
        c.setStatus(status);
        return caseRepo.save(c);
    }

    @Override
    public List<IntegrityCase> getCasesByStudent(Long studentId) {
        return caseRepo.findByStudentProfile_Id(studentId);
    }

    @Override
    public List<IntegrityCase> getAllCases() {
        return caseRepo.findAll();
    }

    @Override
    public Optional<IntegrityCase> getCaseById(Long id) {
        return caseRepo.findById(id);
    }
}