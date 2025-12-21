package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository repository;

public StudentProfileServiceImpl(StudentProfileRepository repository) {
this.repository = repository;
}

public StudentProfile createStudent(StudentProfile studentProfile) {
studentProfile.setRepeatOffender(false);
return repository.save(studentProfile);
}

public StudentProfile getStudentById(Long id) {
return repository.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException("Student not found"));
}

public List<StudentProfile> getAllStudents() {
return repository.findAll();
}

public StudentProfile markRepeatOffender(Long id) {
StudentProfile s = getStudentById(id);
s.setRepeatOffender(true);
return repository.save(s);
}
}
