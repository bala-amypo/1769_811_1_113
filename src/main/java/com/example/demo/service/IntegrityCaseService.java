package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseService {

    IntegrityCase createCase(IntegrityCase integrityCase);

    IntegrityCase updateCaseStatus(Long id, String status);

    List<IntegrityCase> getCasesByStudent(String studentId);

    IntegrityCase getCaseById(Long id);

    List<IntegrityCase> getAllCases();
}
