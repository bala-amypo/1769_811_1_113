package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
@JsonProperty(access = JsonProperty.Access.READ_ONLY)
private boolean repeatOffender = false;

@Column(nullable = false, updatable = false)
@JsonProperty(access = JsonProperty.Access.READ_ONLY)
private LocalDateTime createdAt;

/* 🔐 REQUIRED FOR DB (user_id NOT NULL) */
@ManyToOne(optional = false)
@JoinColumn(name = "user_id", nullable = false)
@JsonIgnore
private AppUser user;

/* Needed for JPA + tests */
@OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
@JsonIgnore
private List<IntegrityCase> integrityCases = new ArrayList<>();

/* ========================= */
/* 🔧 LIFECYCLE CALLBACK    */
/* ========================= */
@PrePersist
public void prePersist() {
this.createdAt = LocalDateTime.now();
}

/* ========================= */
/* ✅ GETTERS & SETTERS     */
/* ========================= */

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

public boolean isRepeatOffender() {
return repeatOffender;
}

public void setRepeatOffender(boolean repeatOffender) {
this.repeatOffender = repeatOffender;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public AppUser getUser() {
return user;
}

/* 🔴 THIS WAS MISSING — FIXES YOUR ERROR */
public void setUser(AppUser user) {
this.user = user;
}

public List<IntegrityCase> getIntegrityCases() {
return integrityCases;
}

public void setIntegrityCases(List<IntegrityCase> integrityCases) {
this.integrityCases = integrityCases;
}
}
