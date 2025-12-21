package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.IntegrityCaseService;

import java.util.List;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository repo;
    private final StudentProfileRepository studentRepo;

    public IntegrityCaseServiceImpl(
            IntegrityCaseRepository repo,
            StudentProfileRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    public IntegrityCase createCase(Long studentId, IntegrityCase c) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Student not found"));

        c.setStudentProfile(student);
        return repo.save(c);
    }

    public IntegrityCase getCaseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Case not found"));
    }

    public List<IntegrityCase> getAllCases() {
        return repo.findAll();
    }
}
