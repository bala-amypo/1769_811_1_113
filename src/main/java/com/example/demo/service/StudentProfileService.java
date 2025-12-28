package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileService {

StudentProfile createStudent(StudentProfile student);

StudentProfile getStudentById(Long id);

List<StudentProfile> getAllStudents();

/* 🔴 EXACT NAME TEST USES */
StudentProfile updateRepeatOffenderStatus(Long studentId);
}
