package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;

@RestController
@RequestMapping("/api/evidence-records")
public class EvidenceRecordController {

    @Autowired
    private EvidenceRecordService evidenceRecordService;

    @PostMapping
    public EvidenceRecord addEvidence(@RequestBody EvidenceRecord evidenceRecord) {
        return evidenceRecordService.addEvidence(evidenceRecord);
    }
}
