package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.ApplicationDto;
import com.example.leasing_backend.entity.Application;

public class ApplicationMapper {

    public static ApplicationDto mapToApplicationDto(Application application) {
        return new ApplicationDto(
                application.getNic(),
                application.getFullname(),
                application.getAddress(),
                application.getMonthlyIncome(),
                application.getAssetOwnership(),
                application.getAssetEstimateValue(),
                application.getBusinessIncome(),
                application.getOtherIncome(),
                application.getEmail(),
                application.getDateOfBirth(),
                application.getProfessions(),
                application.getMobilePhoneNumber(),
                application.getCompanyMobileNo(),
                application.getAssetOwnershipDoc(),
                application.getBirthCertificateImage(),
                application.getNicImage(),
                application.getElectricityBillImg(),
                application.getExpectedLeasingAmount(),
                application.getResidencePhoneNumber(),
                application.getAssetNameAndLocationOfProperty(),
                application.getFixedAllowance(),
                application.getNationality(),
                application.getGuarantorNic(),
                application.getVehicleRegistrationNo(),
                application.getUserId(),
                application.getAccountNo(),
                application.getNameOfEmployee(),
                application.getDesignation(),
                application.getAdminStatus(),
                application.getCustomerStatus(),
                application.getStaffEstimatedAmount(),
                application.getExpectedTimePeriod(),
                application.getStaffEstimatedTimePeriod()
        );
    }

    public static Application mapToApplication(ApplicationDto applicationDto) {
        Application application = new Application();
        application.setNic(applicationDto.getNic());
        application.setFullname(applicationDto.getFullname());
        application.setAddress(applicationDto.getAddress());
        application.setMonthlyIncome(applicationDto.getMonthlyIncome());
        application.setAssetOwnership(applicationDto.getAssetOwnership());
        application.setAssetEstimateValue(applicationDto.getAssetEstimateValue());
        application.setBusinessIncome(applicationDto.getBusinessIncome());
        application.setOtherIncome(applicationDto.getOtherIncome());
        application.setEmail(applicationDto.getEmail());
        application.setDateOfBirth(applicationDto.getDateOfBirth());
        application.setProfessions(applicationDto.getProfessions());
        application.setMobilePhoneNumber(applicationDto.getMobilePhoneNumber());
        application.setCompanyMobileNo(applicationDto.getCompanyMobileNo());
        application.setAssetOwnershipDoc(applicationDto.getAssetOwnershipDoc());
        application.setBirthCertificateImage(applicationDto.getBirthCertificateImage());
        application.setNicImage(applicationDto.getNicImage());
        application.setElectricityBillImg(applicationDto.getElectricityBillImg());
        application.setExpectedLeasingAmount(applicationDto.getExpectedLeasingAmount());
        application.setResidencePhoneNumber(applicationDto.getResidencePhoneNumber());
        application.setAssetNameAndLocationOfProperty(applicationDto.getAssetNameAndLocationOfProperty());
        application.setFixedAllowance(applicationDto.getFixedAllowance());
        application.setNationality(applicationDto.getNationality());
        application.setGuarantorNic(applicationDto.getGuarantorNic());
        application.setVehicleRegistrationNo(applicationDto.getVehicleRegistrationNo());
        application.setUserId(applicationDto.getUserId());
        application.setAccountNo(applicationDto.getAccountNo());
        application.setNameOfEmployee(applicationDto.getNameOfEmployee());
        application.setDesignation(applicationDto.getDesignation());
        application.setAdminStatus(applicationDto.getAdminStatus());
        application.setCustomerStatus(applicationDto.getCustomerStatus());
        application.setStaffEstimatedAmount(applicationDto.getStaffEstimatedAmount());
        application.setExpectedTimePeriod(applicationDto.getExpectedTimePeriod());
        application.setStaffEstimatedTimePeriod(applicationDto.getStaffEstimatedTimePeriod());
        return application;
    }
}
