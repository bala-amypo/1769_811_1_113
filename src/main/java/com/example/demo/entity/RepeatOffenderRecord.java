package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "student_profile_id")
private StudentProfile studentProfile;

@Column(nullable = false)
private Integer totalCases;

private LocalDate firstIncidentDate;

@Column(nullable = false)
private String flagSeverity;

public RepeatOffenderRecord() {}

public RepeatOffenderRecord(StudentProfile studentProfile,
Integer totalCases, LocalDate firstIncidentDate, String flagSeverity) {
this.studentProfile = studentProfile;
this.totalCases = totalCases;
this.firstIncidentDate = firstIncidentDate;
this.flagSeverity = flagSeverity;
}

public Long getId() { return id; }
public StudentProfile getStudentProfile() { return studentProfile; }
public Integer getTotalCases() { return totalCases; }
public String getFlagSeverity() { return flagSeverity; }
}
