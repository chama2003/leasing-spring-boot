package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.StaffDto;
import com.example.leasing_backend.entity.Staff;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.repository.StaffRepository;
import com.example.leasing_backend.service.StaffService;
import com.example.leasing_backend.hash.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffDto createStaff(StaffDto staffDto) {
        // Hash the password before saving
        String hashedPassword = PasswordUtils.hashPassword(staffDto.getPassword());

        Staff staff = new Staff();
        staff.setUsername(staffDto.getUsername());
        staff.setPassword(hashedPassword);  // Set the hashed password
        staff.setRole(staffDto.getRole());

        Staff savedStaff = staffRepository.save(staff);
        return new StaffDto(savedStaff);
    }

    @Override
    public StaffDto getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
        return new StaffDto(staff);
    }

    @Override
    @Transactional
    public StaffDto getStaffByUsername(String username) {
        Staff staff = staffRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with username: " + username));
        return new StaffDto(staff);  // Return StaffDto
    }

    @Override
    public List<StaffDto> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        return staffList.stream()
                .map(StaffDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDto updateStaffById(Long id, StaffDto updatedStaff) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));

        staff.setUsername(updatedStaff.getUsername());
        staff.setPassword(PasswordUtils.hashPassword(updatedStaff.getPassword()));  // Hash the updated password
        staff.setRole(updatedStaff.getRole());

        Staff savedStaff = staffRepository.save(staff);
        return new StaffDto(savedStaff);
    }

    @Override
    public void deleteStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
        staffRepository.deleteById(id);
    }

    @Override
    public boolean verifyStaffPassword(String username, String password) {
        // Retrieve staff by username
        Staff staff = staffRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with username: " + username));

        // Verify the entered password against the stored hashed password
        return PasswordUtils.verifyPassword(password, staff.getPassword());
    }
}
