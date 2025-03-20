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
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_amount")
    private Float paymentAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "remaining_balance")
    private Float remainingBalance;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "status", nullable = false)
    private String status;  // status field to store 'paid' or 'pending'

    @PrePersist
    public void prePersist() {
        // Set default values before saving
        if (status == null) {
            status = "pending"; // Set default status if it's null
        }
    }
}
