package com.example.demo.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String name; // ✅ Ensuring this field exists
    private String role; 

    // --- GETTERS AND SETTERS ---

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // ✅ This is the method the error says is missing
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}