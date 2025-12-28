package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

private final StudentProfileService studentProfileService;

public StudentProfileController(StudentProfileService studentProfileService) {
this.studentProfileService = studentProfileService;
}

@PostMapping
public StudentProfile create(@RequestBody StudentProfile studentProfile) {
return studentProfileService.createStudent(studentProfile);
}

@GetMapping("/{id}")
public StudentProfile getById(@PathVariable Long id) {
return studentProfileService.getStudentById(id);
}
@PreAuthorize("hasRole('ADMIN)")
@GetMapping
public List<StudentProfile> getAll() {
return studentProfileService.getAllStudents();
}

@PutMapping("/{id}/repeat-status")
public StudentProfile updateRepeatStatus(@PathVariable Long id) {
return studentProfileService.updateRepeatOffenderStatus(id);
}
}
