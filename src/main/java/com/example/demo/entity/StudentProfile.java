package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

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

@Column(nullable = false, unique = true)
private String email;

private String program;

@Column(nullable = false)
private Integer yearLevel;

@Column(nullable = false)
private boolean repeatOffender = false;

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

@OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
private List<IntegrityCase> integrityCases;

public StudentProfile() {
}

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

/* ===== getters & setters expected by tests ===== */

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
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

/* 🔑 TEST EXPECTS THIS METHOD NAME */
public boolean getRepeatOffender() {
return repeatOffender;
}

/* (keep isRepeatOffender also if you want) */
public boolean isRepeatOffender() {
return repeatOffender;
}

public void setRepeatOffender(boolean repeatOffender) {
this.repeatOffender = repeatOffender;
}

/* 🔑 TEST EXPECTS THIS */
public LocalDateTime getCreatedAt() {
return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
this.createdAt = createdAt;
}

/* 🔑 TEST EXPECTS THIS */
public List<IntegrityCase> getIntegrityCases() {
return integrityCases;
}

public void setIntegrityCases(List<IntegrityCase> integrityCases) {
this.integrityCases = integrityCases;
}

}
