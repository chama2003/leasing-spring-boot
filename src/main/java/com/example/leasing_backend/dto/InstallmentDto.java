package com.example.leasing_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentDto {

    private Long id;
    private Float paymentAmount;
    private Date paymentDate;
    private Float remainingBalance;
    private Long paymentId;

    private String status;


    // Getters and setters (or you can use Lombok annotations)
}
