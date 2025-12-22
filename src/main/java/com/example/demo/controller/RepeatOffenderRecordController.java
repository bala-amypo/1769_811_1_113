package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RepeatOffenderRecordService;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

private final RepeatOffenderRecordService service;
private final StudentProfileRepository studentRepo;

public RepeatOffenderRecordController(
RepeatOffenderRecordService service,
StudentProfileRepository studentRepo) {

this.service = service;
this.studentRepo = studentRepo;
}


@PostMapping("/refresh/{studentId}")
public RepeatOffenderRecord refresh(@PathVariable String studentId) {

StudentProfile student =
studentRepo.findByStudentId(studentId)
.orElseThrow(() ->
new IllegalArgumentException("student not found"));

return service.refreshRepeatOffender(student);
}


@GetMapping("/student/{studentId}")
public RepeatOffenderRecord getByStudent(@PathVariable String studentId) {

StudentProfile student =
studentRepo.findByStudentId(studentId)
.orElseThrow(() ->
new IllegalArgumentException("student not found"));

return service.getByStudentProfile(student);
}


@GetMapping
public List<RepeatOffenderRecord> getAll() {
return service.getAll();
}
}
