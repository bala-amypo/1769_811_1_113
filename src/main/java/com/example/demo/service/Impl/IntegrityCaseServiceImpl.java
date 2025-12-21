package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository repo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository repo) {
        this.repo = repo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase c) {

        if (c.getStudentProfile() == null) {
            throw new IllegalArgumentException("StudentProfile must be provided");
        }

        return repo.save(c);
    }

    @Override
    public IntegrityCase updateCaseStatus(Long id, String status) {

        IntegrityCase c = repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("IntegrityCase not found"));

        c.setStatus(status);
        return repo.save(c);
    }

    @Override
    public List<IntegrityCase> getCasesByStudent(String studentId) {
        return repo.findByStudentProfile_StudentId(studentId);
    }

    @Override
    public IntegrityCase getCaseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("IntegrityCase not found"));
    }

    @Override
    public List<IntegrityCase> getAllCases() {
        return repo.findAll();
    }
}
