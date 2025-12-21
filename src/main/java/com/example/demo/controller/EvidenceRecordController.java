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
    public EvidenceRecord submit(@RequestBody EvidenceRecord e) {
        return service.submitEvidence(e);
    }

    @GetMapping("/case/{caseId}")
    public List<EvidenceRecord> byCase(@PathVariable Long caseId) {
        return service.getEvidenceByCase(caseId);
    }

    @GetMapping("/{id}")
    public EvidenceRecord get(@PathVariable Long id) {
        return service.getEvidenceById(id);
    }

    @GetMapping
    public List<EvidenceRecord> all() {
        return service.getAllEvidence();
    }
}
