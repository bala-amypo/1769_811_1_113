package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;

//@RestController
//@RequestMapping("/api/evidence")
public class EvidenceRecordController {

private final EvidenceRecordService evidenceRecordService;

public EvidenceRecordController(EvidenceRecordService evidenceRecordService) {
this.evidenceRecordService = evidenceRecordService;
}

@PostMapping
public EvidenceRecord submit(@RequestBody EvidenceRecord evidenceRecord) {
return evidenceRecordService.submitEvidence(evidenceRecord);
}
}
