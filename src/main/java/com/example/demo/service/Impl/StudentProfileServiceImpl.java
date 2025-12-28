package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo, IntegrityCaseRepository caseRepo) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile dto) {
        return studentRepo.save(dto);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile updateRepeatOffenderStatus(Long studentId) {
        StudentProfile student = getStudentById(studentId);
        List<IntegrityCase> cases = caseRepo.findByStudentProfile(student);
        // Rule: >= 2 cases implies repeat offender
        boolean isRepeat = cases.size() >= 2;
        student.setRepeatOffender(isRepeat);
        return studentRepo.save(student);
    }

    @Override
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId);
    }
}