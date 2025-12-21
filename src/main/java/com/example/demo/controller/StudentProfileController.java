package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile create(@RequestBody StudentProfile s) {
        return service.createStudent(s);
    }

    @GetMapping("/{id}")
    public StudentProfile get(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAllStudents();
    }

    @PutMapping("/{studentId}/repeat-status")
    public StudentProfile repeat(@PathVariable String studentId) {
        return service.updateRepeatOffenderStatus(studentId);
    }

    @GetMapping("/lookup/{studentId}")
    public StudentProfile lookup(@PathVariable String studentId) {
        return service.getByStudentIdentifier(studentId);
    }
}
