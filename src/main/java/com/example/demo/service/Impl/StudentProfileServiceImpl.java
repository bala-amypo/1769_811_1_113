package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository repo;

public StudentProfileServiceImpl(StudentProfileRepository repo) {
this.repo = repo;
}

@Override
public StudentProfile createStudent(StudentProfile s) {
try {
return repo.save(s);
} catch (Exception e) {
return null;
}
}

@Override
public StudentProfile getStudentById(Long id) {
return repo.findById(id).orElse(null);
}

@Override
public List<StudentProfile> getAllStudents() {
return repo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(String studentId) {

StudentProfile s = repo.findByStudentId(studentId);

if(s == null) {
return null;
}


s.setRepeatOffender(!s.isRepeatOffender());

try {
return repo.save(s);
} catch (Exception e) {
return s;
}

}

@Override
public StudentProfile getByStudentIdentifier(String studentId) {
return repo.findByStudentId(studentId);
}
}
