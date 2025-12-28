package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String name;

@Column(nullable = false, unique = true)
private String email;

private int yearLevel;

public StudentProfile() {
}

public StudentProfile(String name, String email, int yearLevel) {
this.name = name;
this.email = email;
this.yearLevel = yearLevel;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public int getYearLevel() { return yearLevel; }
public void setYearLevel(int yearLevel) { this.yearLevel = yearLevel; }
}
