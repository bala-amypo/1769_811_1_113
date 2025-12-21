package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseService {

IntegrityCase createCase(IntegrityCase integrityCase);

IntegrityCase getCaseById(Long id);

List<IntegrityCase> getCasesByStudent(Long studentId);

IntegrityCase updateStatus(Long id, String status);
}
