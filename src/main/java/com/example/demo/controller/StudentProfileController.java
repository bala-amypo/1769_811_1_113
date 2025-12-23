package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController{

@Autowired
StudentProfileService service;

@PostMapping
public ResponseEntity<?> createStudent(@RequestBody StudentProfile student){
try{
return ResponseEntity.ok(service.createStudent(student));
}catch(RuntimeException e){
return ResponseEntity.badRequest().body(e.getMessage());
}
}

@PutMapping("/{studentId}/repeat-offender")
public ResponseEntity<?> updateRepeatOffender(@PathVariable String studentId,@RequestParam boolean repeatOffender){
try{
return ResponseEntity.ok(service.updateRepeatOffender(studentId,repeatOffender));
}catch(RuntimeException e){
return ResponseEntity.badRequest().body(e.getMessage());
}
}

@GetMapping
public List<StudentProfile> getAllStudents(){
return service.getAllStudents();
}

@GetMapping("/id/{id}")
public ResponseEntity<?> getById(@PathVariable Long id){
try{
return ResponseEntity.ok(service.getById(id));
}catch(RuntimeException e){
return ResponseEntity.badRequest().body("User not found");
}
}

@GetMapping("/student/{studentId}")
public ResponseEntity<?> getByStudentId(@PathVariable String studentId){
try{
return ResponseEntity.ok(service.getByStudentId(studentId));
}catch(RuntimeException e){
return ResponseEntity.badRequest().body("User not found");
}
}
}
