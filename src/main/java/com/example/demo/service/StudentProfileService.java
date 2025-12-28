package com.example.demo.service;
import com.example.demo.entity.StudentProfile;
import java.util.List;
import java.util.Optional;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile dto);
    StudentProfile getStudentById(Long id);
    List<StudentProfile> getAllStudents();
    StudentProfile updateRepeatOffenderStatus(Long studentId);
    Optional<StudentProfile> findByStudentId(String studentId); // Supports 'lookup' endpoint
}