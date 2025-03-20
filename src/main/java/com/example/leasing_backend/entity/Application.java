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
@Table(name = "application")
public class Application {

    @Id
    @Column(name = "nic", unique = true)
    private Long nic;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "address")
    private String address;

    @Column(name = "monthly_income")
    private double monthlyIncome;

    @Column(name = "asset_ownership")
    private String assetOwnership;

    @Column(name = "asset_estimate_value")
    private double assetEstimateValue;

    @Column(name = "business_income")
    private double businessIncome;

    @Column(name = "other_income")
    private double otherIncome;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "professions")
    private String professions;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "company_mobile_no")
    private String companyMobileNo;

    @Column(name = "asset_ownership_doc")
    private String assetOwnershipDoc;

    @Column(name = "birth_certificate_image")
    private String birthCertificateImage;

    @Column(name = "nic_image")
    private String nicImage;

    @Column(name = "electricity_bill_img")
    private String electricityBillImg;

    @Column(name = "expected_leasing_amount")
    private double expectedLeasingAmount;

    @Column(name = "residence_phonenumber")
    private String residencePhoneNumber;

    @Column(name = "asset_name_and_location_of_property")
    private String assetNameAndLocationOfProperty;

    @Column(name = "fixed_allowance")
    private double fixedAllowance;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "guarantor_nic")
    private Long guarantorNic;

    @Column(name = "vehicle_registration_no")
    private String vehicleRegistrationNo;

    @Column(name = "admin_id")
    private Long adminId = 1L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "name_of_employee")
    private String nameOfEmployee;

    @Column(name = "designation")
    private String designation;

    // Admin status - default value "pending"
    @Column(name = "admin_status", nullable = false)
    private String adminStatus;

    // Customer status - default value "pending"
    @Column(name = "customer_status", nullable = false)
    private String customerStatus;

    // Staff estimated amount - default value 0.0
    @Column(name = "staff_estimated_amount")
    private double staffEstimatedAmount;

    @Column(name = "expected_time_period")
    private int expectedTimePeriod;  // Expected time period (month count)

    @Column(name = "staff_estimated_time_period")
    private int staffEstimatedTimePeriod;  // Staff estimated time period (month count)

    @PrePersist
    public void prePersist() {
        // Set default values before saving
        if (adminStatus == null) {
            adminStatus = "pending"; // Set default admin status if it's null
        }
        if (customerStatus == null) {
            customerStatus = "pending"; // Set default customer status if it's null
        }
        if (staffEstimatedAmount == 0.0) {
            staffEstimatedAmount = 0.0; // Ensure staffEstimatedAmount is 0.0
        }
        if (expectedTimePeriod == 0) {
            expectedTimePeriod = 12; // Default expected time period set to 12 months
        }
        if (staffEstimatedTimePeriod == 0) {
            staffEstimatedTimePeriod = 12; // Default staff estimated time period set to 12 months
        }
    }
}
