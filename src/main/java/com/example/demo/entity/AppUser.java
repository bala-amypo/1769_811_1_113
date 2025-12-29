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

@Column(unique = true, nullable = false)
private String email;

@Column(nullable = false)
private String password;


@Column(name = "full_name", nullable = false)
private String fullName;


@Column(nullable = false)
private boolean enabled = true;

@Column(name = "created_at", updatable = false)
private LocalDateTime createdAt;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}



public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }

public String getFullName() { return fullName; }
public void setFullName(String fullName) { this.fullName = fullName; }

public boolean isEnabled() { return enabled; }
public void setEnabled(boolean enabled) { this.enabled = enabled; }

public LocalDateTime getCreatedAt() { return createdAt; }

public Set<Role> getRoles() { return roles; }
public void setRoles(Set<Role> roles) { this.roles = roles; }
}
