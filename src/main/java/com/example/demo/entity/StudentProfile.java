package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "user_id")
private AppUser user;

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
private boolean repeatOffender = false;

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

@ManyToOne(
mappedBy = "studentProfile",
cascade = CascadeType.ALL,
orphanRemoval = true
)
private List<IntegrityCase> integrityCases = new ArrayList<>();

public StudentProfile() {
this.createdAt = LocalDateTime.now();
}

/* getters and setters */

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

public boolean getRepeatOffender() {
return repeatOffender;
}

public boolean isRepeatOffender() {
return repeatOffender;
}

public void setRepeatOffender(boolean repeatOffender) {
this.repeatOffender = repeatOffender;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public List<IntegrityCase> getIntegrityCases() {
return integrityCases;
}


public void setCreatedAt(LocalDateTime createdAt) {
this.createdAt = createdAt;
}



}
