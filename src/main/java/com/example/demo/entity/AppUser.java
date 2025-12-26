package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_users")
public class AppUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false)
private String password;

@Column(nullable = false)
private String fullName;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<StudentProfile> studentProfiles = new HashSet<>();

public AppUser() {
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
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

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
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
}
