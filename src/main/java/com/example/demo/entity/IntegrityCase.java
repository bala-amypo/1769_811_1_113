package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "student_profile_id")
private StudentProfile studentProfile;

private String courseCode;
private String description;
private String status = "OPEN";
private LocalDate incidentDate;

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

@PrePersist
void onCreate() {
this.createdAt = LocalDateTime.now();
}

public Long getId() { return id; }
public StudentProfile getStudentProfile() { return studentProfile; }
public void setStudentProfile(StudentProfile studentProfile) {
this.studentProfile = studentProfile;
}
}
