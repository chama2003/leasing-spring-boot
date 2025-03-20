package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
    PaymentDto updatePaymentById(Long id, PaymentDto updatedPayment);
    void deletePaymentById(Long id);

    // New methods to work with customer username
    List<PaymentDto> getPaymentsByCustomerUsername(String customerUsername);
    PaymentDto updatePaymentByCustomerUsername(String customerUsername, PaymentDto updatedPayment);
}
