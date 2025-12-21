package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    @PostMapping
    public IntegrityCase create(@RequestBody IntegrityCase c) {
        return service.createCase(c);
    }

    @PutMapping("/{id}/status")
    public IntegrityCase update(@PathVariable Long id,
                                @RequestParam String status) {
        return service.updateCaseStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<IntegrityCase> byStudent(@PathVariable String studentId) {
        return service.getCasesByStudent(studentId);
    }

    @GetMapping("/{id}")
    public IntegrityCase get(@PathVariable Long id) {
        return service.getCaseById(id);
    }

    @GetMapping
    public List<IntegrityCase> all() {
        return service.getAllCases();
    }
    
}
