package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@JsonIgnore
private Long id;

@ManyToOne(optional = true)
@JoinColumn(name = "user_id", nullable = true)
@JsonIgnore
private AppUser user;

@Column(nullable = false, unique = true)
private String studentId;

@Column(nullable = false)
private String name;

@Column(nullable = false, unique = true)
private String email;

private String program;

@Column(nullable = false)
private Integer yearLevel;

@Column(nullable = false)
@JsonIgnore
private boolean repeatOffender = false;

@Column(nullable = false, updatable = false)
@JsonIgnore
private LocalDateTime createdAt;

@OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
private List<IntegrityCase> integrityCases = new ArrayList<>();

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

/* getters & setters */
}




