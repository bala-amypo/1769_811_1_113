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

    public StudentProfile createStudent(StudentProfile s) {
        return repo.save(s);
    }

    public StudentProfile getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<StudentProfile> getAllStudents() {
        return repo.findAll();
    }

    public StudentProfile updateRepeatOffenderStatus(String studentId) {
        StudentProfile s = repo.findByStudentId(studentId);
        if (s != null) {
            s.setRepeatOffender(true);
            return repo.save(s);
        }
        return null;
    }

    public StudentProfile getByStudentIdentifier(String studentId) {
        return repo.findByStudentId(studentId);
    }
}
