package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "student_profile_id", nullable = false, unique = true)
    private StudentProfile studentProfile;

    private int caseCount;

    private boolean repeatOffender;

    private LocalDateTime lastUpdated;

    // ✅ Default constructor (required by JPA)
    public RepeatOffenderRecord() {
    }

    // ✅ Parameterized constructor
    public RepeatOffenderRecord(StudentProfile studentProfile,
                                int caseCount,
                                boolean repeatOffender) {
        this.studentProfile = studentProfile;
        this.caseCount = caseCount;
        this.repeatOffender = repeatOffender;
        this.lastUpdated = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    private void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    // Getters & Setters

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

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public boolean isRepeatOffender() {
        return repeatOffender;
    }

    public void setRepeatOffender(boolean repeatOffender) {
        this.repeatOffender = repeatOffender;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
