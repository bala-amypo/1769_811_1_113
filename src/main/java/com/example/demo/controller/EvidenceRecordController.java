package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService service;

    public EvidenceRecordController(EvidenceRecordService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<EvidenceRecord> submitNewEvidence(@RequestBody EvidenceRecord evidence) {
        return ResponseEntity.ok(service.submitEvidence(evidence));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<EvidenceRecord>> getEvidenceForCase(@PathVariable Long caseId) {
        return ResponseEntity.ok(service.getEvidenceByCase(caseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenceRecord> getEvidenceById(@PathVariable Long id) {
        return service.getEvidenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EvidenceRecord>> listAllEvidence() {
        return ResponseEntity.ok(service.getAllEvidence());
    }
}