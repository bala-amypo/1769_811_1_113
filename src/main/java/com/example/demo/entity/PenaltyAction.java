package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="penalty_actions")
public class PenaltyAction {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name="case_id", nullable=false)
private IntegrityCase integrityCase;

private String penaltyType;
private String details;
private String issuedBy;
private LocalDateTime issuedAt;

@PrePersist
void onIssue() {
issuedAt = LocalDateTime.now();
}
}
