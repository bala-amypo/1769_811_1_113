package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "student_id", nullable = false)
private StudentProfile studentProfile;

private String courseCode;
private String instructorName;
private String description;
private String status = "OPEN";
private LocalDate incidentDate;
private LocalDateTime createdAt = LocalDateTime.now();

public IntegrityCase() {}

public Long getId() { return id; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
}
