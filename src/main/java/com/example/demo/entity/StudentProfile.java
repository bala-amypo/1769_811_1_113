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

@ManyToOne(optional = false)
@JoinColumn(name = "user_id", nullable = false)
private AppUser user;

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
private Boolean repeatOffender = false;

@Column(nullable = false)
private LocalDateTime createdAt = LocalDateTime.now();

@OneToMany(mappedBy = "studentProfile")
private List<IntegrityCase> integrityCases = new ArrayList<>();

public StudentProfile() {}

public void setCreatedAt(LocalDateTime createdAt) {
this.createdAt = createdAt;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getStudentId() { return studentId; }
public void setStudentId(String studentId) { this.studentId = studentId; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getProgram() { return program; }
public void setProgram(String program) { this.program = program; }

public Integer getYearLevel() { return yearLevel; }
public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }

public Boolean getRepeatOffender() { return repeatOffender; }
public void setRepeatOffender(Boolean repeatOffender) { this.repeatOffender = repeatOffender; }

public LocalDateTime getCreatedAt() { return createdAt; }
public AppUser getUser() {
return user;
}

public void setUser(AppUser user) {
this.user = user;
}

public List<IntegrityCase> getIntegrityCases() { return integrityCases; }
}
