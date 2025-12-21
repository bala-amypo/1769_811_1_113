package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One record per student
    @OneToOne(optional = false)
    @JoinColumn(name = "student_profile_id", nullable = false, unique = true)
    private StudentProfile studentProfile;

    // Number of integrity cases for the student
    private int caseCount;

    // Whether the student is officially marked as repeat offender
    private boolean repeatOffender;

    // Last time this record was recalculated
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    private void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    // -------- getters & setters --------

    public Long getId() {
        return id;
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
}
