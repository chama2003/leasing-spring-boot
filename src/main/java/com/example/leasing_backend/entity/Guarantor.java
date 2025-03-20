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
@Table(name = "details_of_guaranters")
public class Guarantor {

    @Id
    @Column(name = "guarantor_NIC", unique = true)
    private Long guarantorNic;  // Unique NIC for the guarantor

    @Column(name = "guarantor_nic_img")
    private String guarantorNicImg;  // Image for the NIC of the guarantor

    @Column(name = "guarantors_name")
    private String guarantorsName;  // Name of the guarantor

    @Column(name = "guarantor_occupation")
    private String guarantorOccupation;  // Occupation of the guarantor

    @Column(name = "guarantor_income")
    private double guarantorIncome;  // Income of the guarantor
}
