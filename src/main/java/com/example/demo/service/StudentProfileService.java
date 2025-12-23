package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileService{

StudentProfile createStudent(StudentProfile student);

StudentProfile updateRepeatOffender(String studentId,boolean repeatOffender);

List<StudentProfile> getAllStudents();

StudentProfile getById(Long id);

StudentProfile getByStudentId(String studentId);
}
