package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import java.util.List;
import java.util.Optional;

public interface StudentProfileRepository {

Optional<StudentProfile> findById(Long id);

List<StudentProfile> findAll();

StudentProfile save(StudentProfile studentProfile);
}
