package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    @Autowired
    private IntegrityCaseRepository integrityCaseRepository;

    @Override
    public IntegrityCase createCase(IntegrityCase integrityCase) {
        if (integrityCase.getStudentProfile() == null) {
            throw new IllegalArgumentException("StudentProfile cannot be null");
        }
        return integrityCaseRepository.save(integrityCase);
    }

    @Override
    public IntegrityCase getCaseById(Long id) {
        return integrityCaseRepository.findById(id).orElse(null);
    }

    @Override
    public List<IntegrityCase> getAllCases() {
        return integrityCaseRepository.findAll();
    }

    @Override
    public IntegrityCase updateCaseStatus(Long id, String status) {
        IntegrityCase integrityCase = integrityCaseRepository.findById(id).orElse(null);
        if (integrityCase != null) {
            integrityCase.setStatus(status);
            return integrityCaseRepository.save(integrityCase);
        }
        return null;
    }
}
