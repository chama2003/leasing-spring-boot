package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.ApplicationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

    ApplicationDto createApplication(ApplicationDto applicationDto);

    ApplicationDto getApplicationById(Long nic);

    List<ApplicationDto> getAllApplications();

    ApplicationDto updateApplicationById(Long nic, ApplicationDto updatedApplication);

    void deleteApplicationById(Long nic);

    // New methods
    ApplicationDto updateAdminStatus(Long nic, String status);

    ApplicationDto updateCustomerStatus(Long nic, String status);
    boolean doesNicExist(Long nic);
    ApplicationDto updateStaffEstimatedAmount(Long nic, double amount);
    ApplicationDto updateStaffEstimatedTimePeriod(Long nic, int timePeriod);
    List<ApplicationDto> getApplicationsByUserId(String userId);
}
