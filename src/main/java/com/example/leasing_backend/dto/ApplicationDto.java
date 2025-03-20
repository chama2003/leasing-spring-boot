package com.example.leasing_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private Long nic;
    private String fullname;
    private String address;
    private double monthlyIncome;
    private String assetOwnership;
    private double assetEstimateValue;
    private double businessIncome;
    private double otherIncome;
    private String email;
    private String dateOfBirth;
    private String professions;
    private String mobilePhoneNumber;
    private String companyMobileNo;
    private String assetOwnershipDoc;
    private String birthCertificateImage;
    private String nicImage;
    private String electricityBillImg;
    private double expectedLeasingAmount;
    private String residencePhoneNumber;
    private String assetNameAndLocationOfProperty;
    private double fixedAllowance;
    private String nationality;
    private Long guarantorNic;
    private String vehicleRegistrationNo;

    private String userId;
    private String accountNo;
    private String nameOfEmployee;
    private String designation;

    // Admin status
    private String adminStatus;

    // Customer status
    private String customerStatus;

    // Staff estimated amount
    private double staffEstimatedAmount;
    private int expectedTimePeriod;  // Expected time period (month count)
    private int staffEstimatedTimePeriod;
}
