package com.example.leasing_backend.controller;

import com.example.leasing_backend.entity.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final Admin admin = new Admin();  // Creating an instance of Admin with hardcoded values

    // Endpoint to get admin credentials
    @GetMapping("/api/admin")
    public ResponseEntity<Admin> getAdminDetails() {
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Endpoint to get admin username and password for authentication
    @GetMapping("/api/admin/credentials")
    public ResponseEntity<String> getAdminCredentials() {
        return ResponseEntity.ok("Username: " + admin.getUsername()
                + ", Password: " + admin.getPassword()
                + ", Role: " + admin.getRole());
    }

}
