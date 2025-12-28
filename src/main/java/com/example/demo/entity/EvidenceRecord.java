package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence_records")
public class EvidenceRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "case_id")
private IntegrityCase integrityCase;

@Column(nullable = false)
private String evidenceType;

@Column(nullable = false)
private String content;

@Column(nullable = false)
private String submittedBy;

@Column(nullable = false)
private LocalDateTime submittedAt = LocalDateTime.now();

public EvidenceRecord() {}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public void setSubmittedAt(LocalDateTime submittedAt) {
this.submittedAt = submittedAt;
}

public IntegrityCase getIntegrityCase() { return integrityCase; }
public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }

public String getEvidenceType() { return evidenceType; }
public void setEvidenceType(String evidenceType) { this.evidenceType = evidenceType; }

public String getContent() { return content; }
public void setContent(String content) { this.content = content; }

public String getSubmittedBy() { return submittedBy; }
public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }

public LocalDateTime getSubmittedAt() { return submittedAt; }
}
