package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "evidence_records")
public class EvidenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "integrity_case_id", nullable = false)
    private IntegrityCase integrityCase;

    private String evidenceType;

    @Column(length = 2000)
    private String content;

    private String submittedBy;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

   
    public EvidenceRecord() {
    }

  
    public EvidenceRecord(
            IntegrityCase integrityCase,
            String evidenceType,
            String content,
            String submittedBy,
            LocalDateTime submittedAt) {

        this.integrityCase = integrityCase;
        this.evidenceType = evidenceType;
        this.content = content;
        this.submittedBy = submittedBy;
        this.submittedAt = submittedAt;
    }

    
    @PrePersist
    public void prePersist() {
        if (submittedAt == null) {
            submittedAt = LocalDateTime.now();
        }
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {       
        this.id = id;
    }

    public IntegrityCase getIntegrityCase() {
        return integrityCase;
    }

    public void setIntegrityCase(IntegrityCase integrityCase) {
        this.integrityCase = integrityCase;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
