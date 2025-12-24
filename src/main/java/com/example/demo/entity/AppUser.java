package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
name = "app_users",
uniqueConstraints = {
@UniqueConstraint(columnNames = "email")
}
)
public class AppUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String fullName;

@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false)
private String password;

@Column(nullable = false)
private Boolean enabled = true;

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private Set<StudentProfile> studentProfiles = new HashSet<>();

public AppUser() {}

public AppUser(String fullName,String email,String password) {
this.fullName = fullName;
this.email = email;
this.password = password;
this.enabled = true;
}

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public Boolean getEnabled() {
return enabled;
}

public void setEnabled(Boolean enabled) {
this.enabled = enabled;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public Set<Role> getRoles() {
return roles;
}

public void setRoles(Set<Role> roles) {
this.roles = roles;
}

public Set<StudentProfile> getStudentProfiles() {
return studentProfiles;
}

public void setStudentProfiles(Set<StudentProfile> studentProfiles) {
this.studentProfiles = studentProfiles;
}
public void setId(long id){
this.id=id;
}
