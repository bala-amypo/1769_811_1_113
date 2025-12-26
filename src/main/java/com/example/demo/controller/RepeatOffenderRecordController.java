package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

private final RepeatOffenderRecordService service;

public RepeatOffenderRecordController(
RepeatOffenderRecordService service
) {
this.service = service;
}

@PostMapping("/refresh/{studentId}")
public RepeatOffenderRecord refresh(@PathVariable Long studentId) {
return service.refreshRepeatOffenderData(studentId);
}

@GetMapping("/student/{studentId}")
public RepeatOffenderRecord getByStudent(@PathVariable Long studentId) {
return service.getRecordByStudent(studentId);
}

@GetMapping
public List<RepeatOffenderRecord> getAll() {
return service.getAllRepeatOffenders();
}
}
