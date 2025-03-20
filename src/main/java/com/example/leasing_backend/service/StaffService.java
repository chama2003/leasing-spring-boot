package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.StaffDto;
import java.util.List;

public interface StaffService {

    StaffDto createStaff(StaffDto staffDto);

    StaffDto getStaffById(Long id);

    List<StaffDto> getAllStaff();

    StaffDto updateStaffById(Long id, StaffDto updatedStaff);

    void deleteStaffById(Long id);
    boolean verifyStaffPassword(String username, String password);
    // Corrected method in the interface
    StaffDto getStaffByUsername(String username); // Added the method signature for getting staff by username
}
