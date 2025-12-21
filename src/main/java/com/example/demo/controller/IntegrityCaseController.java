package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

private final IntegrityCaseService service;

public IntegrityCaseController(IntegrityCaseService service) {
this.service = service;
}

@PostMapping
public IntegrityCase create(@RequestBody IntegrityCase c) {
return service.createCase(c);
}

@GetMapping("/{id}")
public IntegrityCase get(@PathVariable Long id) {
return service.getCaseById(id);
}

@GetMapping("/student/{studentId}")
public List<IntegrityCase> byStudent(@PathVariable Long studentId) {
return service.getCasesByStudent(studentId);
}

@PutMapping("/{id}/status")
public IntegrityCase updateStatus(
@PathVariable Long id,
@RequestParam String status) {
return service.updateStatus(id, status);
}
}
