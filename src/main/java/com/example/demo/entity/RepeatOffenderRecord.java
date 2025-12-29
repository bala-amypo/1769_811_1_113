package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "student_profile_id", nullable = false)
private StudentProfile studentProfile;

/* ❗ KEEP studentId ONLY IF YOU REALLY NEED IT */
@Column(name = "student_id", nullable = false)
private Long studentId;

@Column(nullable = false)
private int totalCases;

@Column(nullable = false)
private String flagSeverity;

private LocalDate lastIncidentDate;

/* getters + setters */

public void setStudentProfile(StudentProfile studentProfile) {
this.studentProfile = studentProfile;
}

public void setStudentId(Long studentId) {
this.studentId = studentId;
}

public void setTotalCases(int totalCases) {
this.totalCases = totalCases;
}

public void setFlagSeverity(String flagSeverity) {
this.flagSeverity = flagSeverity;
}

public void setLastIncidentDate(LocalDate lastIncidentDate) {
this.lastIncidentDate = lastIncidentDate;
}
}
