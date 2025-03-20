package com.example.leasing_backend.dto;

import com.example.leasing_backend.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {

    private Long id;
    private String username;
    private String password;
    private String role;

    // Constructor to convert from Staff entity
    public StaffDto(Staff staff) {
        this.id = staff.getId();
        this.username = staff.getUsername();
        this.password = staff.getPassword();
        this.role = staff.getRole();
    }
}
