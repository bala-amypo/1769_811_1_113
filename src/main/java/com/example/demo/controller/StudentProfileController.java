package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles")
public class StudentProfileController {

private final StudentProfileService studentProfileService;

public StudentProfileController(StudentProfileService studentProfileService) {
this.studentProfileService = studentProfileService;
}

@PostMapping
public StudentProfile createStudent(@RequestBody StudentProfile student) {
return studentProfileService.createStudent(student);
}

@GetMapping("/{id}")
public StudentProfile getStudentById(@PathVariable Long id) {
return studentProfileService.getStudentById(id);
}

@GetMapping
public List<StudentProfile> getAllStudents() {
return studentProfileService.getAllStudents();
}

@PutMapping("/{studentId}/repeat-offender-status")
public StudentProfile updateRepeatOffenderStatus(@PathVariable Long studentId) {
return studentProfileService.updateRepeatOffenderStatus(studentId);
}
}
