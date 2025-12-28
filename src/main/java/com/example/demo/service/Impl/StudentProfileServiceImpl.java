package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository repo;

public StudentProfileServiceImpl(StudentProfileRepository repo) {
this.repo = repo;
}

@Override
public StudentProfile createStudent(StudentProfile student) {
return repo.save(student);
}

@Override
public StudentProfile getStudentById(Long id) {
return repo.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found")
);
}

@Override
public List<StudentProfile> getAllStudents() {
return repo.findAll();
}

@Override
public void deleteStudent(Long id) {
StudentProfile student = getStudentById(id);
repo.delete(student);
}
}
