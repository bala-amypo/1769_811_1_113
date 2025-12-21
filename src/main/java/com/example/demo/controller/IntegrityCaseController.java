package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "instructor_name")
    private String instructorName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "incident_date")
    private LocalDate incidentDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<EvidenceRecord> evidenceRecords;

   
    public IntegrityCase() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    
    public IntegrityCase(String courseCode,
                         String instructorName,
                         String description,
                         LocalDate incidentDate,
                         StudentProfile studentProfile) {
        this.courseCode = courseCode;
        this.instructorName = instructorName;
        this.description = description;
        this.incidentDate = incidentDate;
        this.studentProfile = studentProfile;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

   

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public List<EvidenceRecord> getEvidenceRecords() {
        return evidenceRecords;
    }

    public void setEvidenceRecords(List<EvidenceRecord> evidenceRecords) {
        this.evidenceRecords = evidenceRecords;
    }
}
