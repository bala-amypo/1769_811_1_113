package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

    private final RepeatOffenderRecordService service;

    public RepeatOffenderRecordController(RepeatOffenderRecordService service) { this.service = service; }

    @PostMapping("/refresh/{studentId}")
    public ResponseEntity<RepeatOffenderRecord> recalculateRepeatOffenderStatus(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.refreshRepeatOffenderData(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<RepeatOffenderRecord> getRecordForStudent(@PathVariable Long studentId) {
        return service.getRecordByStudent(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RepeatOffenderRecord>> listAllRepeatOffenders() {
        return ResponseEntity.ok(service.getAllRepeatOffenders());
    }
}