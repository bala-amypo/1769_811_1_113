package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String studentId;

private String name;
private String email;
private String program;

private Integer yearLevel;

private Boolean repeatOffender = false;

private LocalDateTime createdAt;

@OneToMany(mappedBy = "studentProfile")
private List<IntegrityCase> integrityCases;

@PrePersist
void onCreate() {
this.createdAt = LocalDateTime.now();
}

public StudentProfile() {}

public StudentProfile(String studentId,String name,String email,String program,Integer yearLevel) {
this.studentId = studentId;
this.name = name;
this.email = email;
this.program = program;
this.yearLevel = yearLevel;
}
}
