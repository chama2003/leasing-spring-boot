package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.StaffDto;
import com.example.leasing_backend.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin("*")
public class StaffController {

    private final StaffService staffService;

    // Constructor to inject the StaffService
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Create a new staff
    @PostMapping
    public ResponseEntity<StaffDto> createStaff(@RequestBody StaffDto staffDto) {
        StaffDto savedStaff = staffService.createStaff(staffDto);
        return ResponseEntity.status(201).body(savedStaff);
    }

    // Get staff by ID
    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long id) {
        StaffDto staffDto = staffService.getStaffById(id);
        return ResponseEntity.ok(staffDto);
    }

    // Get staff by username
    @GetMapping("user/{username}")
    public ResponseEntity<StaffDto> getStaffByUsername(@PathVariable("username") String username) {
        StaffDto staffDto = staffService.getStaffByUsername(username); // Use the instance of StaffService
        return ResponseEntity.ok(staffDto);
    }

    // Get all staff
    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        List<StaffDto> staffDtos = staffService.getAllStaff();
        return ResponseEntity.ok(staffDtos);
    }

    // Update staff by ID
    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaff(@PathVariable Long id, @RequestBody StaffDto updatedStaff) {
        StaffDto updatedStaffDto = staffService.updateStaffById(id, updatedStaff);
        return ResponseEntity.ok(updatedStaffDto);
    }

    // Delete staff by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaffById(@PathVariable Long id) {
        staffService.deleteStaffById(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPassword(@RequestParam String username, @RequestParam String password) {
        boolean isPasswordCorrect = staffService.verifyStaffPassword(username, password);
        if (isPasswordCorrect) {
            return ResponseEntity.ok("Password is correct");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }

}
