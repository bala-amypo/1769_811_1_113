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
@JoinColumn(name = "integrity_case_id", nullable = false)
private IntegrityCase integrityCase;


@Column(nullable = false)
private String evidenceType;

@Column(nullable = false, length = 3000)
private String content;

@Column(nullable = false)
private String submittedBy;

@Column(nullable = false, updatable = false)
private LocalDateTime submittedAt;

public EvidenceRecord() {}

public EvidenceRecord(IntegrityCase integrityCase,String evidenceType,String content,String submittedBy) {
this.integrityCase = integrityCase;
this.evidenceType = evidenceType;
this.content = content;
this.submittedBy = submittedBy;
}

@PrePersist
protected void onCreate() {
this.submittedAt = LocalDateTime.now();
}

public Long getId() {
return id;
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
public void setId(Long id) {
this.id = id;
}

public void setSubmittedAt(LocalDateTime submittedAt) {
this.submittedAt = submittedAt;
}

}
