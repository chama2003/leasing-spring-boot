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
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", unique = true)
    private String username;  // Username

    @Column(name = "password")
    private String password;  // Password

    @Column(name = "role")
    private String role;

    @Column(name = "admin_id")
    private Long adminId = 1L;  // Admin ID (Fixed value of 1)

}
