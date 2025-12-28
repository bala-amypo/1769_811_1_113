package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    private Integer totalCases;
    private String flagSeverity; // LOW, MEDIUM, HIGH
    private LocalDate lastIncidentDate;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }
    public Integer getTotalCases() { return totalCases; }
    public void setTotalCases(Integer totalCases) { this.totalCases = totalCases; }
    public String getFlagSeverity() { return flagSeverity; }
    public void setFlagSeverity(String flagSeverity) { this.flagSeverity = flagSeverity; }
    public LocalDate getLastIncidentDate() { return lastIncidentDate; }
    public void setLastIncidentDate(LocalDate lastIncidentDate) { this.lastIncidentDate = lastIncidentDate; }
}