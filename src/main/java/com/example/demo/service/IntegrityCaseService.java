package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseService {

    IntegrityCase createCase(IntegrityCase integrityCase);

    IntegrityCase getCaseById(Long id);

    List<IntegrityCase> getAllCases();

    IntegrityCase updateCaseStatus(Long id, String status);
}
