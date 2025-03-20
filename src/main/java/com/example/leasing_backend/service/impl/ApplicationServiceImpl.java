package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.ApplicationDto;
import com.example.leasing_backend.entity.Application;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.ApplicationMapper;
import com.example.leasing_backend.repository.ApplicationRepository;
import com.example.leasing_backend.service.ApplicationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    @Transactional
    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        // Map DTO to Entity
        Application application = ApplicationMapper.mapToApplication(applicationDto);

        // Save application
        Application savedApplication = applicationRepository.save(application);

        // Map saved application entity to DTO and return
        return ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    @Transactional
    public ApplicationDto getApplicationById(Long nic) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));
        return ApplicationMapper.mapToApplicationDto(application);
    }

    @Override
    @Transactional
    public List<ApplicationDto> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream()
                .map(ApplicationMapper::mapToApplicationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplicationDto updateApplicationById(Long nic, ApplicationDto updatedApplication) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));

        // Update application fields
        application.setFullname(updatedApplication.getFullname());
        application.setAddress(updatedApplication.getAddress());
        application.setMonthlyIncome(updatedApplication.getMonthlyIncome());
        application.setAssetOwnership(updatedApplication.getAssetOwnership());
        application.setAssetEstimateValue(updatedApplication.getAssetEstimateValue());
        application.setBusinessIncome(updatedApplication.getBusinessIncome());
        application.setOtherIncome(updatedApplication.getOtherIncome());
        application.setEmail(updatedApplication.getEmail());
        application.setDateOfBirth(updatedApplication.getDateOfBirth());
        application.setProfessions(updatedApplication.getProfessions());
        application.setMobilePhoneNumber(updatedApplication.getMobilePhoneNumber());
        application.setCompanyMobileNo(updatedApplication.getCompanyMobileNo());
        application.setAssetOwnershipDoc(updatedApplication.getAssetOwnershipDoc());
        application.setBirthCertificateImage(updatedApplication.getBirthCertificateImage());
        application.setNicImage(updatedApplication.getNicImage());
        application.setElectricityBillImg(updatedApplication.getElectricityBillImg());

        application.setExpectedLeasingAmount(updatedApplication.getExpectedLeasingAmount());
        application.setResidencePhoneNumber(updatedApplication.getResidencePhoneNumber());
        application.setAssetNameAndLocationOfProperty(updatedApplication.getAssetNameAndLocationOfProperty());
        application.setFixedAllowance(updatedApplication.getFixedAllowance());
        application.setNationality(updatedApplication.getNationality());
        application.setGuarantorNic(updatedApplication.getGuarantorNic());
        application.setVehicleRegistrationNo(updatedApplication.getVehicleRegistrationNo());

        Application savedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    @Transactional
    public void deleteApplicationById(Long nic) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));
        applicationRepository.deleteById(nic);
    }

    @Override
    @Transactional
    public ApplicationDto updateAdminStatus(Long nic, String status) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));

        if ("pending".equals(status) || "approved".equals(status) || "rejected".equals(status)) {
            application.setAdminStatus(status);
        } else {
            throw new IllegalArgumentException("Invalid status value. Allowed values are: pending, approved, rejected.");
        }

        Application updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(updatedApplication);
    }

    @Override
    @Transactional
    public ApplicationDto updateCustomerStatus(Long nic, String status) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));

        if ("pending".equals(status) || "approved".equals(status) || "rejected".equals(status)) {
            application.setCustomerStatus(status);
        } else {
            throw new IllegalArgumentException("Invalid status value. Allowed values are: pending, approved, rejected.");
        }

        Application updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(updatedApplication);
    }

    @Override
    @Transactional
    public ApplicationDto updateStaffEstimatedAmount(Long nic, double amount) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));

        application.setStaffEstimatedAmount(amount);

        Application updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(updatedApplication);
    }
    @Override
    @Transactional
    public ApplicationDto updateStaffEstimatedTimePeriod(Long nic, int timePeriod) {
        Application application = applicationRepository.findById(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with NIC: " + nic));

        application.setStaffEstimatedTimePeriod(timePeriod);

        Application updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(updatedApplication);
    }
    @Override
    @Transactional
    public List<ApplicationDto> getApplicationsByUserId(String userId) {
        List<Application> applications = applicationRepository.findByUserId(userId);
        if (applications.isEmpty()) {
            throw new ResourceNotFoundException("No applications found for user with ID: " + userId);
        }
        return applications.stream()
                .map(ApplicationMapper::mapToApplicationDto)
                .collect(Collectors.toList());
    }
    @Override
    public boolean doesNicExist(Long nic) {
        return applicationRepository.existsByNic(nic);  // Check if NIC exists using the repository
    }
}
