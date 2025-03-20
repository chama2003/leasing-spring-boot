package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    // Custom query to find installments by payment ID
    List<Installment> findByPaymentId(Long paymentId);
}
