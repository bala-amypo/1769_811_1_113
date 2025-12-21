package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/api/integrity-cases")
public class IntegrityCaseController {

    @Autowired
    private IntegrityCaseService integrityCaseService;

    @PostMapping
    public IntegrityCase createCase(@RequestBody IntegrityCase integrityCase) {
        return integrityCaseService.createCase(integrityCase);
    }

    @GetMapping("/{id}")
    public IntegrityCase getCaseById(@PathVariable Long id) {
        return integrityCaseService.getCaseById(id);
    }

    @GetMapping
    public List<IntegrityCase> getAllCases() {
        return integrityCaseService.getAllCases();
    }

    @PutMapping("/{id}/status")
    public IntegrityCase updateCaseStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return integrityCaseService.updateCaseStatus(id, status);
    }
}
