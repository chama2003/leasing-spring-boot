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
@Table(name = "vehicle_details")
public class VehicleDetails {

    @Id
    @Column(name = "vehicle_registration_no", unique = true)
    private String vehicleRegistrationNo;  // Vehicle Registration Number (Primary Key)

    @Column(name = "vehicle_brand")
    private String vehicleBrand;  // Vehicle brand (e.g., Toyota, Honda)

    @Column(name = "vehicle_model")
    private String vehicleModel;  // Vehicle model (e.g., Corolla, Civic)

    @Column(name = "YOM")
    private int yearOfManufacture;  // Year of Manufacture

    @Column(name = "YOR")
    private int yearOfRegistration;  // Year of Registration

    @Column(name = "engine_number")
    private String engineNumber;  // Engine number

    @Column(name = "chassi_number")
    private String chassiNumber;  // Chassis number

    @Column(name = "number_of_owner")
    private int numberOfOwner;  // Number of owners

    @Column(name = "exterior_color")
    private String exteriorColor;  // Exterior color

    @Column(name = "interior_color")
    private String interiorColor;  // Interior color

    @Column(name = "vehicle_book_image")
    private String vehicleBookImage;  // Image of the vehicle's book

    @Column(name = "vehicle_front_img")
    private String vehicleFrontImg;  // Image of the front of the vehicle

    @Column(name = "vehicle_rear_img")
    private String vehicleRearImg;  // Image of the rear of the vehicle

    @Column(name = "vehicle_right_img")
    private String vehicleRightImg;  // Image of the right side of the vehicle
}
