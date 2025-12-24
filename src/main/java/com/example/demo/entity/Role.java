package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String name;

public Role() {}
public Role(String name) { this.name = name; }

public String getName() { return name; }
}
