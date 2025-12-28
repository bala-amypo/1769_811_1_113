package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cases")
@Tag(name = "Integrity Cases")
public class IntegrityCaseController {

private final IntegrityCaseService integrityCaseService;

public IntegrityCaseController(IntegrityCaseService integrityCaseService) {
this.integrityCaseService = integrityCaseService;
}

@PostMapping
public IntegrityCase createCase(@RequestBody IntegrityCase integrityCase) {
return integrityCaseService.createCase(integrityCase);
}

@PutMapping("/{id}/status")
public IntegrityCase updateCaseStatus(
@PathVariable Long id,
@RequestParam String status
) {
return integrityCaseService.updateCaseStatus(id, status);
}

@GetMapping("/student/{studentId}")
public List<IntegrityCase> getCasesByStudent(@PathVariable Long studentId) {
return integrityCaseService.getCasesByStudent(studentId);
}

@GetMapping("/{id}")
public Optional<IntegrityCase> getCaseById(@PathVariable Long id) {
return integrityCaseService.getCaseById(id);
}

@GetMapping
public List<IntegrityCase> getAllCases() {
return integrityCaseService.getAllCases();
}
}
