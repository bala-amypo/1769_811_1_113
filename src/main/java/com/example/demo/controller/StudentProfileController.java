package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile dto) {
        return new ResponseEntity<>(service.createStudent(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> listAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @PutMapping("/{studentId}/repeat-status")
    public ResponseEntity<StudentProfile> updateRepeatOffenderStatus(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.updateRepeatOffenderStatus(studentId));
    }

    // New Endpoint from Image Description
    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> findByStudentIdentifier(@PathVariable String studentId) {
        return service.findByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}