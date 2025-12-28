package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evidence")
@Tag(name = "Evidence Records")
public class EvidenceRecordController {

private final EvidenceRecordService evidenceRecordService;

public EvidenceRecordController(EvidenceRecordService evidenceRecordService) {
this.evidenceRecordService = evidenceRecordService;
}

@PostMapping
public EvidenceRecord submitEvidence(@RequestBody EvidenceRecord evidence) {
return evidenceRecordService.submitEvidence(evidence);
}

@GetMapping("/case/{caseId}")
public List<EvidenceRecord> getEvidenceByCase(@PathVariable Long caseId) {
return evidenceRecordService.getEvidenceByCase(caseId);
}

@GetMapping("/{id}")
public Optional<EvidenceRecord> getEvidenceById(@PathVariable Long id) {
return evidenceRecordService.getEvidenceById(id);
}

@GetMapping
public List<EvidenceRecord> getAllEvidence() {
return evidenceRecordService.getAllEvidence();
}
}
