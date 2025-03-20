package com.example.leasing_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "username", unique = true)
    private String username;  // Primary Key

    @Column(name = "password")
    private String password;

    @Column(name = "user_username")
    private String user_username;

    @Column(name = "role")
    private String role;

    @Column(name = "admin_id")
    private Long adminId = 1L;  // Admin ID (Fixed value of 1)

    // Getters and setters
}

