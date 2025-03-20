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
public class PaymentDto {

    private Long id;
    private String paymentPlan;
    private Float amountPaid;
    private Float totalAmount;
    private Date startDate;
    private Float interestRate;
    private Integer numberOfInstallments;
    private String customerUsername;



    // Getters and setters (You can use Lombok annotations if you prefer)
}
