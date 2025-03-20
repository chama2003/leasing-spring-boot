package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query to find payments by customer username
    List<Payment> findByCustomer(String customerUsername);
}
