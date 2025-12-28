package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repeat-offenders")
@Tag(name = "Repeat Offenders")
public class RepeatOffenderRecordController {

private final RepeatOffenderRecordService repeatOffenderRecordService;

public RepeatOffenderRecordController(
RepeatOffenderRecordService repeatOffenderRecordService
) {
this.repeatOffenderRecordService = repeatOffenderRecordService;
}

@PostMapping("/refresh/{studentId}")
public RepeatOffenderRecord refreshRepeatOffenderData(
@PathVariable Long studentId
) {
return repeatOffenderRecordService.refreshRepeatOffenderData(studentId);
}

@GetMapping("/student/{studentId}")
public Optional<RepeatOffenderRecord> getRecordByStudent(
@PathVariable Long studentId
) {
return repeatOffenderRecordService.getRecordByStudent(studentId);
}

@GetMapping
public List<RepeatOffenderRecord> getAllRepeatOffenders() {
return repeatOffenderRecordService.getAllRepeatOffenders();
}
}
