package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
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

@Column(name = "full_name", nullable = false)
private String fullName;

@Column(name = "created_at", nullable = false, updatable = false)
private LocalDateTime createdAt;

@Column(nullable = false)
private boolean enabled;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();

/* ===================== LIFECYCLE ===================== */

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
this.enabled = true;
}

/* ===================== GETTERS ===================== */

public Long getId() {
return id;
}

public String getEmail() {
return email;
}

public String getPassword() {
return password;
}

public String getFullName() {
return fullName;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public boolean isEnabled() {
return enabled;
}

public Set<Role> getRoles() {
return roles;
}

/* ===================== SETTERS ===================== */

public void setId(Long id) {
this.id = id;
}

public void setEmail(String email) {
this.email = email;
}

public void setPassword(String password) {
this.password = password;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public void setCreatedAt(LocalDateTime createdAt) {
this.createdAt = createdAt;
}

public void setEnabled(boolean enabled) {
this.enabled = enabled;
}

public void setRoles(Set<Role> roles) {
this.roles = roles;
}
}
