package com.example.leasing_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_plan")
    private String paymentPlan;

    @Column(name = "amount_paid")
    private Float amountPaid;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "interest_rate")
    private Float interestRate;

    @Column(name = "no_of_installment")
    private Integer numberOfInstallments;


    @Column(name = "customer_username")
    private String customer;
}
