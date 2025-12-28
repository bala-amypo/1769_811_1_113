package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

@ManyToOne
@JoinColumn(name = "user_id")
private AppUser user;

@OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
private List<IntegrityCase> integrityCases = new ArrayList<>();

public StudentProfile() {}

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

/* 🔴 REQUIRED BY TESTS */
public void setId(Long id) { this.id = id; }
public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
public boolean getRepeatOffender() { return repeatOffender; }

/* normal getters/setters */
public Long getId() { return id; }
public String getStudentId() { return studentId; }
public void setStudentId(String studentId) { this.studentId = studentId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public Integer getYearLevel() { return yearLevel; }
public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }
public void setRepeatOffender(boolean repeatOffender) {
this.repeatOffender = repeatOffender;
}
}
