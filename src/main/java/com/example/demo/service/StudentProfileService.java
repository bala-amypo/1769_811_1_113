package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileService {

StudentProfile createStudent(StudentProfile student);

StudentProfile getStudentById(Long id);

List<StudentProfile> getAllStudents();

/* REQUIRED BY TESTS */
StudentProfile updateRepeatOffenderStatus(Long studentId);
}
