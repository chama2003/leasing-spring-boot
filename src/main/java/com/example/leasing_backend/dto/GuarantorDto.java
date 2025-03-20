package com.example.leasing_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorDto {

    private Long guarantorNic;  // Guarantor's NIC
    private String guarantorNicImg;  // Guarantor's NIC image
    private String guarantorsName;  // Guarantor's name
    private String guarantorOccupation;  // Guarantor's occupation
    private double guarantorIncome;  // Guarantor's income
}
