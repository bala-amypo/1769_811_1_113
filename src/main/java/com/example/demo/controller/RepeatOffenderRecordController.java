package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

    private final RepeatOffenderRecordService service;

    public RepeatOffenderRecordController(
            RepeatOffenderRecordService service) {
        this.service = service;
    }

    @PostMapping("/refresh/{studentId}")
    public ResponseEntity<RepeatOffenderRecord> refresh(
            @PathVariable String studentId) {

        return ResponseEntity.ok(
                service.refreshRepeatOffenderData(studentId));
    }

    @GetMapping
    public List<RepeatOffenderRecord> all() {
        return service.getAllRepeatOffenders();
    }

    @GetMapping("/student/{studentId}")
    public RepeatOffenderRecord byStudent(
            @PathVariable String studentId) {

        return service.getRecordByStudent(studentId);
    }
}
