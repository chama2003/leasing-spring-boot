package com.example.leasing_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsDto {

    private String vehicleRegistrationNo;  // Vehicle Registration Number
    private String vehicleBrand;  // Vehicle brand
    private String vehicleModel;  // Vehicle model
    private int yearOfManufacture;  // Year of manufacture
    private int yearOfRegistration;  // Year of registration
    private String engineNumber;  // Engine number
    private String chassiNumber;  // Chassis number
    private int numberOfOwner;  // Number of owners
    private String exteriorColor;  // Exterior color
    private String interiorColor;  // Interior color
    private String vehicleBookImage;  // Vehicle book image URL
    private String vehicleFrontImg;  // Front image URL
    private String vehicleRearImg;  // Rear image URL
    private String vehicleRightImg;  // Right side image URL
}
