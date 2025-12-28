package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_actions")
public class PenaltyAction {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "case_id")
private IntegrityCase integrityCase;

@Column(nullable = false)
private String penaltyType;

@Column(nullable = false)
private String details;

@Column(nullable = false)
private String issuedBy;

@Column(nullable = false)
private LocalDateTime issuedAt = LocalDateTime.now();

public PenaltyAction() {}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public IntegrityCase getIntegrityCase() { return integrityCase; }
public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }

public String getPenaltyType() { return penaltyType; }
public void setPenaltyType(String penaltyType) { this.penaltyType = penaltyType; }

public String getDetails() { return details; }
public void setDetails(String details) { this.details = details; }

public String getIssuedBy() { return issuedBy; }
public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }

public LocalDateTime getIssuedAt() { return issuedAt; }
}
