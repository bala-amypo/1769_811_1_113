package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository repo;

public StudentProfileServiceImpl(StudentProfileRepository repo) {
this.repo = repo;
}

@Override
public StudentProfile createStudent(StudentProfile studentProfile) {
return repo.save(studentProfile);
}

@Override
public StudentProfile getStudentById(Long id) {
return repo.findById(id)
.orElseThrow(() ->
new IllegalArgumentException("student not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return repo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(String studentId) {

StudentProfile student =
repo.findByStudentId(studentId)
.orElseThrow(() ->
new IllegalArgumentException("student not found"));

student.setRepeatOffender(true);

return repo.save(student);
}

@Override
public StudentProfile getByStudentIdentifier(String studentId) {

return repo.findByStudentId(studentId)
.orElseThrow(() ->
new IllegalArgumentException("student not found"));
}
}
