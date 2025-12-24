package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
name = "roles",
uniqueConstraints = {
@UniqueConstraint(columnNames = "name")
}
)
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@ManyToMany(mappedBy = "roles")
private Set<AppUser> users = new HashSet<>();

public Role() {}

public Role(String name) {
this.name = name;
}

public Long getId() {
return id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Set<AppUser> getUsers() {
return users;
}

public void setUsers(Set<AppUser> users) {
this.users = users;
}
}
