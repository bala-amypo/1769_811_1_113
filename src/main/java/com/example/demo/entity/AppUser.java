package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AppUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String email;

private String password;

@ManyToOne
private Role role;

public Long getId() { return id; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }
public Role getRole() { return role; }
public void setRole(Role role) { this.role = role; }
}
