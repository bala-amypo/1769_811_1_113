package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId; // The unique identifier string (e.g., "S1234")

    private String name;
    private String email;
    private String program;
    private Integer yearLevel;
    private boolean repeatOffender = false; // Rule: Default false
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<IntegrityCase> integrityCases = new ArrayList<>();

    @PrePersist
    protected void onCreate() { this.createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public Integer getYearLevel() { return yearLevel; }
    public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }
    public boolean isRepeatOffender() { return repeatOffender; }
    public void setRepeatOffender(boolean repeatOffender) { this.repeatOffender = repeatOffender; }
    public List<IntegrityCase> getIntegrityCases() { return integrityCases; }
    public void setIntegrityCases(List<IntegrityCase> integrityCases) { this.integrityCases = integrityCases; }
}