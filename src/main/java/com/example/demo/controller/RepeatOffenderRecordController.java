package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

    @Autowired
    private RepeatOffenderRecordService service;

    // Increment case count & update repeat offender status
    @PostMapping("/refresh/{studentId}")
    public RepeatOffenderRecord refresh(@PathVariable Long studentId) {
        return service.createOrUpdate(studentId);
    }

    @GetMapping("/{id}")
    public RepeatOffenderRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<RepeatOffenderRecord> getAll() {
        return service.getAll();
    }
}
