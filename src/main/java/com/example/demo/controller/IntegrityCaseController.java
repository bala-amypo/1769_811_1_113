package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {

private final IntegrityCaseService integrityCaseService;

public IntegrityCaseController(IntegrityCaseService integrityCaseService) {
this.integrityCaseService = integrityCaseService;
}

@PostMapping
public IntegrityCase create(@RequestBody IntegrityCase integrityCase) {
return integrityCaseService.createCase(integrityCase);
}

@PutMapping("/{id}/status")
public IntegrityCase updateStatus(
@PathVariable Long id,
@RequestParam String status
) {
return integrityCaseService.updateCaseStatus(id,status);
}

@GetMapping("/student/{studentId}")
public List<IntegrityCase> getByStudent(@PathVariable Long studentId) {
return integrityCaseService.getCasesByStudent(studentId);
}

@GetMapping("/{id}")
public Optional<IntegrityCase> getById(@PathVariable Long id) {
return integrityCaseService.getCaseById(id);
}
}
