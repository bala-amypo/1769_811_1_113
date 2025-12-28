package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<IntegrityCase> createNewIntegrityCase(@RequestBody IntegrityCase integrityCase) {
        return ResponseEntity.ok(service.createCase(integrityCase));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<IntegrityCase> updateCaseStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateCaseStatus(id, status));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<IntegrityCase>> getCasesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getCasesByStudent(studentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntegrityCase> getCaseById(@PathVariable Long id) {
        return service.getCaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<IntegrityCase>> listAllCases() {
        return ResponseEntity.ok(service.getAllCases());
    }
}