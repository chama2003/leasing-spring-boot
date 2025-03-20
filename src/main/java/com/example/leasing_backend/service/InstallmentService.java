package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.InstallmentDto;

import java.util.List;

public interface InstallmentService {

    InstallmentDto createInstallment(InstallmentDto installmentDto);
    InstallmentDto getInstallmentById(Long id);
    List<InstallmentDto> getAllInstallments();
    InstallmentDto updateInstallmentById(Long id, InstallmentDto updatedInstallment);
    void deleteInstallmentById(Long id);

    // New method to get installments by paymentId
    List<InstallmentDto> getInstallmentsByPaymentId(Long paymentId);
}
