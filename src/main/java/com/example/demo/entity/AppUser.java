package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
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

@Column(nullable = false)
private LocalDateTime createdAt;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles;

@PrePersist
public void onCreate() {
this.createdAt = LocalDateTime.now();
}

public AppUser() {}

public AppUser(String fullName, String email, String password) {
this.fullName = fullName;
this.email = email;
this.password = password;
}
}
