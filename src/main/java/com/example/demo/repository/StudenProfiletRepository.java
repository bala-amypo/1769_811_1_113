package com.example.demo.repository;
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.StudentProfile;
import java.util.Optional;

public interface StudentProfileRepository
extends JpaRepository<StudentProfile, Long> {

Optional<StudentProfile> findByStudentId(String studentId);
}
