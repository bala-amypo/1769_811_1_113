package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository repo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository repo) {
        this.repo = repo;
    }

    public IntegrityCase createCase(IntegrityCase c) {
        return repo.save(c);
    }

    public IntegrityCase updateCaseStatus(Long id, String status) {
        IntegrityCase c = repo.findById(id).orElse(null);
        if (c != null) {
            c.setStatus(status);
            return repo.save(c);
        }
        return null;
    }

    public List<IntegrityCase> getCasesByStudent(String studentId) {
        return repo.findByStudentProfile_StudentId(studentId);
    }

    public IntegrityCase getCaseById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<IntegrityCase> getAllCases() {
        return repo.findAll();
    }
}
