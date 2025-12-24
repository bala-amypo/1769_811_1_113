package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="integrity_cases")
public class IntegrityCase {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name="student_profile_id", nullable=false)
private StudentProfile studentProfile;

private String courseCode;
private String instructorName;
private String description;
private String status = "OPEN";
private LocalDate incidentDate;
private LocalDateTime createdAt = LocalDateTime.now();

@OneToMany(mappedBy="integrityCase")
private List<EvidenceRecord> evidenceRecords;

@OneToMany(mappedBy="integrityCase")
private List<PenaltyAction> penaltyActions;
}
