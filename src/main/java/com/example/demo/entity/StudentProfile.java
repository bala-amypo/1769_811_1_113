package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String studentId;

@Column(nullable = false)
private String name;

@Column(nullable = false)
private String email;

private String program;

@Column(nullable = false)
private Integer yearLevel;

@Column(nullable = false)
private Boolean repeatOffender = false;

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

@OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<IntegrityCase> integrityCases = new HashSet<>();

public StudentProfile() {}

public StudentProfile(String studentId,String name,String email,String program,Integer yearLevel) {
this.studentId = studentId;
this.name = name;
this.email = email;
this.program = program;
this.yearLevel = yearLevel;
this.repeatOffender = false;
}

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public String getStudentId() {
return studentId;
}

public void setStudentId(String studentId) {
this.studentId = studentId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getProgram() {
return program;
}

public void setProgram(String program) {
this.program = program;
}

public Integer getYearLevel() {
return yearLevel;
}

public void setYearLevel(Integer yearLevel) {
this.yearLevel = yearLevel;
}

public Boolean getRepeatOffender() {
return repeatOffender;
}

public void setRepeatOffender(Boolean repeatOffender) {
this.repeatOffender = repeatOffender;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public Set<IntegrityCase> getIntegrityCases() {
return integrityCases;
}

public void setIntegrityCases(Set<IntegrityCase> integrityCases) {
this.integrityCases = integrityCases;
}
}
