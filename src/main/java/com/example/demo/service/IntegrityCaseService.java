package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseService {
IntegrityCase createCase(IntegrityCase integrityCase);
IntegrityCase updateCaseStatus(Long caseId,String status);
List<IntegrityCase> getCasesByStudent(Long studentId);
IntegrityCase getCaseById(Long id);
}
