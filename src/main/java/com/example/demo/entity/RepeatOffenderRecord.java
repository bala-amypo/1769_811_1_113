package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@OneToOne(optional = false)
@JoinColumn(name = "student_id")
private StudentProfile studentProfile;

@Column(nullable = false)
private Integer totalCases;

@Column(nullable = false)
private String flagSeverity;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public StudentProfile getStudentProfile() {
return studentProfile;
}

public void setStudentProfile(StudentProfile studentProfile) {
this.studentProfile = studentProfile;
}

public Integer getTotalCases() {
return totalCases;
}

public void setTotalCases(Integer totalCases) {
this.totalCases = totalCases;
}

public String getFlagSeverity() {
return flagSeverity;
}

public void setFlagSeverity(String flagSeverity) {
this.flagSeverity = flagSeverity;
}
}
