package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

private final StudentProfileService studentProfileService;

public StudentProfileController(StudentProfileService studentProfileService) {
this.studentProfileService = studentProfileService;
}

/* ================= CREATE STUDENT ================= */
@PostMapping
public ResponseEntity<StudentProfile> createStudent(
@RequestBody StudentProfile student
) {
StudentProfile created =
studentProfileService.createStudent(student);

return new ResponseEntity<>(created, HttpStatus.CREATED);
}

/* ================= GET BY ID ================= */
@GetMapping("/{id}")
public ResponseEntity<StudentProfile> getStudentById(
@PathVariable Long id
) {
StudentProfile student =
studentProfileService.getStudentById(id);

return ResponseEntity.ok(student);
}

/* ================= GET ALL ================= */
@GetMapping
public ResponseEntity<List<StudentProfile>> getAllStudents() {
return ResponseEntity.ok(
studentProfileService.getAllStudents()
);
}

/* ================= UPDATE REPEAT OFFENDER ================= */
@PutMapping("/{id}/repeat-status")
public ResponseEntity<StudentProfile> updateRepeatOffenderStatus(
@PathVariable Long id
) {
StudentProfile updated =
studentProfileService.updateRepeatOffenderStatus(id);

return ResponseEntity.ok(updated);
}
}
