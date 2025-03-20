package com.example.leasing_backend.entity;

public class Admin {

    private final long adminId = 1;  // Admin ID (Fixed value)
    private final String username = "admin";  // Admin Username (Fixed value)
    private final String password = "admin";  // Admin Password (Fixed value)
    private final String role = "admin";  // Admin Role (Fixed value)

    // Getter for adminId
    public long getAdminId() {
        return adminId;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for role
    public String getRole() {
        return role;
    }
}
