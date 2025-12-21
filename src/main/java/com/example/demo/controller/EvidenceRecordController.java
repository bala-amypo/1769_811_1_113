package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService service;

    public EvidenceRecordController(EvidenceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public EvidenceRecord submit(
            @RequestBody EvidenceRecord evidenceRecord) {
        return service.submitEvidence(evidenceRecord);
    }

    @GetMapping("/{id}")
    public EvidenceRecord getById(@PathVariable Long id) {
        return service.getEvidenceById(id);
    }

    @GetMapping("/case/{caseId}")
    public List<EvidenceRecord> getByCase(
            @PathVariable Long caseId) {
        return service.getEvidenceByCase(caseId);
    }

    @GetMapping
    public List<EvidenceRecord> all() {
        return service.getAllEvidence();
    }
}
