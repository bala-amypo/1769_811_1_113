package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {

private final IntegrityCaseService caseService;

public IntegrityCaseController(IntegrityCaseService caseService) {
this.caseService = caseService;
}

@PostMapping
public IntegrityCase createCase(@RequestBody IntegrityCase integrityCase) {
return caseService.createCase(integrityCase);
}

@PutMapping("/{caseId}/status")
public IntegrityCase updateCaseStatus(
@PathVariable Long caseId,
@RequestParam String status) {
return caseService.updateCaseStatus(caseId, status);
}

@GetMapping("/student/{studentId}")
public List<IntegrityCase> getCasesByStudent(@PathVariable Long studentId) {
return caseService.getCasesByStudent(studentId);
}

@GetMapping("/{caseId}")
public Optional<IntegrityCase> getCaseById(@PathVariable Long caseId) {
return caseService.getCaseById(caseId);
}
}
