package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class StudentProfile {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)

 private Long id;
 @Column(unique=true)
 private String studentid;
 @Column(unique = true)
 @Email
 @NotBlank
 private String email;
 private String Program;
 private int yearlevel;
 @ManyToMany
 private List<IntegrityCase>IntegrityCases;
 private boolean isRepeatOffender;
 private LocalDateTime createdAt;
 public Long getId() {
    return id;
 }
 
 public StudentProfile(Long id, String studentid, String email, String program, int yearlevel,
        List<IntegrityCase> integrityCases, boolean isRepeatOffender, LocalDateTime createdAt) {
    this.id = id;
    this.studentid = studentid;
    this.email = email;
    Program = program;
    this.yearlevel = yearlevel;
    IntegrityCases = integrityCases;
    this.isRepeatOffender = isRepeatOffender;
    this.createdAt = createdAt;
}


 public StudentProfile() {
}

 public void setId(Long id) {
    this.id = id;
 }
 public String getStudentid() {
    return studentid;
 }
 public void setStudentid(String studentid) {
    this.studentid = studentid;
 }
 public String getEmail() {
    return email;
 }
 public void setEmail(String email) {
    this.email = email;
 }
 public String getProgram() {
    return Program;
 }
 public void setProgram(String program) {
    Program = program;
 }
 public int getYearlevel() {
    return yearlevel;
 }
 public void setYearlevel(int yearlevel) {
    this.yearlevel = yearlevel;
 }
 public boolean isRepeatOffender() {
    return isRepeatOffender;
 }
 public void setRepeatOffender(boolean isRepeatOffender) {
    this.isRepeatOffender = isRepeatOffender;
 }
 public LocalDateTime getCreatedAt() {
    return createdAt;
 }
 public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
 }
 public List<IntegrityCase> getIntegrityCases() {
    return IntegrityCases;
 }
 public void setIntegrityCases(List<IntegrityCase> integrityCases) {
    IntegrityCases = integrityCases;
 }
 
}
