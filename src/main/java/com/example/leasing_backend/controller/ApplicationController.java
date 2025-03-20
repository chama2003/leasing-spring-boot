package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.ApplicationDto;
import com.example.leasing_backend.dto.TestimonialDto;
import com.example.leasing_backend.service.ApplicationService;

import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final StorageService storageService;

    public ApplicationController(ApplicationService applicationService, StorageService storageService) {
        this.applicationService = applicationService;
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto savedApplication = applicationService.createApplication(applicationDto);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<ApplicationDto> createApp(
            @RequestParam("AssetOwnershipDoc") MultipartFile AssetOwnershipDoc,
            @RequestParam("BirthCertificateImage") MultipartFile BirthCertificateImage,
            @RequestParam("NicImage") MultipartFile NicImage,
            @RequestParam("ElectricityBillImg") MultipartFile ElectricityBillImg,
            @RequestParam("Fullname") String Fullname,
            @RequestParam("nameOfEmployee") String nameOfEmployee,
            @RequestParam("nic") Long nic,
            @RequestParam("Address") String Address,
            @RequestParam("accountNo") String accountno,
            @RequestParam("monthlyIncome") Double monthlyIncome,
            @RequestParam("AssetOwnership") String AssetOwnership,
            @RequestParam("AssetEstimateValue") Double AssetEstimateValue,
            @RequestParam("BusinessIncome") Double BusinessIncome,
            @RequestParam("OtherIncome") Double OtherIncome,
            @RequestParam("Email") String Email,
            @RequestParam("designation") String designation,
            @RequestParam("DateOfBirth") String DateOfBirth,
            @RequestParam("Professions") String Professions,
            @RequestParam("MobilePhoneNumber") String MobilePhoneNumber,
            @RequestParam("CompanyMobileNo") String CompanyMobileNo,
            @RequestParam("ExpectedLeasingAmount") Double ExpectedLeasingAmount,
            @RequestParam("ExpectedLesingPeriod") int expectedlesingperiod,
            @RequestParam("ResidencePhoneNumber") String ResidencePhoneNumber,
            @RequestParam("AssetNameAndLocationOfProperty") String AssetNameAndLocationOfProperty,
            @RequestParam("FixedAllowance") Double FixedAllowance,
            @RequestParam("Nationality") String Nationality,
            @RequestParam("userId") String userId,
            @RequestParam("GuarantorNic") Long GuarantorNic,
            @RequestParam("VehicleRegistrationNo") String VehicleRegistrationNo) {

        try {
            // Validate if NIC exists in the database
            if (applicationService.doesNicExist(nic)) {
                // Returning an error message with BAD_REQUEST status
                ErrorResponse errorResponse = new ErrorResponse("NIC already exists in the database.");
                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }

            // Upload the files via StorageService
            String fileNameAssetOwnershipDoc = storageService.uploadImage(AssetOwnershipDoc);
            String fileNameBirthCertificateImage = storageService.uploadImage(BirthCertificateImage);
            String fileNameNicImage = storageService.uploadImage(NicImage);
            String fileNameElectricityBillImg = storageService.uploadImage(ElectricityBillImg);

            // Create and populate the ApplicationDto
            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setFullname(Fullname);
            applicationDto.setNic(nic);
            applicationDto.setAccountNo(accountno);
            applicationDto.setAddress(Address);
            applicationDto.setMonthlyIncome(monthlyIncome);
            applicationDto.setAssetOwnership(AssetOwnership);
            applicationDto.setAssetEstimateValue(AssetEstimateValue);
            applicationDto.setBusinessIncome(BusinessIncome);
            applicationDto.setOtherIncome(OtherIncome);
            applicationDto.setEmail(Email);
            applicationDto.setDesignation(designation);
            applicationDto.setDateOfBirth(DateOfBirth);
            applicationDto.setProfessions(Professions);
            applicationDto.setUserId(userId);
            applicationDto.setMobilePhoneNumber(MobilePhoneNumber);
            applicationDto.setExpectedTimePeriod(expectedlesingperiod);
            applicationDto.setCompanyMobileNo(CompanyMobileNo);
            applicationDto.setAssetOwnershipDoc(fileNameAssetOwnershipDoc);
            applicationDto.setBirthCertificateImage(fileNameBirthCertificateImage);
            applicationDto.setNicImage(fileNameNicImage);
            applicationDto.setElectricityBillImg(fileNameElectricityBillImg);
            applicationDto.setNameOfEmployee(nameOfEmployee);
            applicationDto.setExpectedLeasingAmount(ExpectedLeasingAmount);
            applicationDto.setResidencePhoneNumber(ResidencePhoneNumber);
            applicationDto.setAssetNameAndLocationOfProperty(AssetNameAndLocationOfProperty);
            applicationDto.setFixedAllowance(FixedAllowance);
            applicationDto.setNationality(Nationality);
            applicationDto.setGuarantorNic(GuarantorNic);
            applicationDto.setVehicleRegistrationNo(VehicleRegistrationNo);

            // Save the ApplicationDto and return the response
            ApplicationDto savedTestimonial = applicationService.createApplication(applicationDto);
            return new ResponseEntity<>(savedTestimonial, HttpStatus.CREATED);

        } catch (IOException e) {
            // Handle file upload errors
            ErrorResponse errorResponse = new ErrorResponse("Error uploading files: " + e.getMessage());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Handle other errors
            ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ErrorResponse class for error messages
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    // Global exception handler for the controller
    @RestControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ErrorResponse class to send error messages



    @GetMapping("{nic}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("nic") Long nic) {
        ApplicationDto applicationDto = applicationService.getApplicationById(nic);
        return ResponseEntity.ok(applicationDto);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        List<ApplicationDto> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("{nic}")
    public ResponseEntity<ApplicationDto> updateApplicationById(@PathVariable("nic") Long nic,
                                                                @RequestBody ApplicationDto updatedApplication) {
        ApplicationDto applicationDto = applicationService.updateApplicationById(nic, updatedApplication);
        return ResponseEntity.ok(applicationDto);
    }
    @GetMapping("/check-nic/{nic}")
    public ResponseEntity<Map<String, Boolean>> checkNic(@PathVariable Long nic) {
        boolean exists = applicationService.doesNicExist(nic);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{nic}")
    public ResponseEntity<String> deleteApplicationById(@PathVariable("nic") Long nic) {
        applicationService.deleteApplicationById(nic);
        return ResponseEntity.ok("Application deleted successfully");
    }

    @PutMapping("{nic}/admin-status")
    public ResponseEntity<ApplicationDto> updateAdminStatus(@PathVariable("nic") Long nic,
                                                            @RequestParam("status") String status) {
        ApplicationDto updatedApplication = applicationService.updateAdminStatus(nic, status);
        return ResponseEntity.ok(updatedApplication);
    }

    @PutMapping("{nic}/customer-status")
    public ResponseEntity<ApplicationDto> updateCustomerStatus(
            @PathVariable("nic") Long nic,
            @RequestParam("status") String status) {
        ApplicationDto updatedApplication = applicationService.updateCustomerStatus(nic, status);
        return ResponseEntity.ok(updatedApplication);
    }


    @PutMapping("{nic}/staff-estimated-amount")
    public ResponseEntity<ApplicationDto> updateStaffEstimatedAmount(@PathVariable("nic") Long nic,
                                                                     @RequestParam("amount") double amount) {
        ApplicationDto updatedApplication = applicationService.updateStaffEstimatedAmount(nic, amount);
        return ResponseEntity.ok(updatedApplication);
    }

    @PutMapping("{nic}/staff-estimated-time-period")
    public ResponseEntity<ApplicationDto> updateStaffEstimatedTimePeriod(@PathVariable("nic") Long nic,
                                                                         @RequestParam("timePeriod") int timePeriod) {
        ApplicationDto updatedApplication = applicationService.updateStaffEstimatedTimePeriod(nic, timePeriod);
        return ResponseEntity.ok(updatedApplication);
    }

    // Download Asset Ownership Document
    @GetMapping("/download/asset-ownership/{fileName}")
    public ResponseEntity<byte[]> downloadAssetOwnershipDoc(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download Birth Certificate Image
    @GetMapping("/download/birth-certificate/{fileName}")
    public ResponseEntity<byte[]> downloadBirthCertificateImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download NIC Image
    @GetMapping("/download/nic-image/{fileName}")
    public ResponseEntity<byte[]> downloadNicImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download Electricity Bill Image
    @GetMapping("/download/electricity-bill/{fileName}")
    public ResponseEntity<byte[]> downloadElectricityBillImg(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }




}