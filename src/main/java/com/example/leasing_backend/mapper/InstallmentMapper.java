package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.InstallmentDto;
import com.example.leasing_backend.entity.Installment;

public class InstallmentMapper {

    public static InstallmentDto mapToInstallmentDto(Installment installment) {
        return new InstallmentDto(
                installment.getId(),
                installment.getPaymentAmount(),
                installment.getPaymentDate(),
                installment.getRemainingBalance(),
                installment.getPaymentId(),
                installment.getStatus()  // Map the payment ID
        );
    }

    public static Installment mapToInstallment(InstallmentDto installmentDto) {
        Installment installment = new Installment();
        installment.setId(installmentDto.getId());
        installment.setPaymentAmount(installmentDto.getPaymentAmount());
        installment.setPaymentDate(installmentDto.getPaymentDate());
        installment.setRemainingBalance(installmentDto.getRemainingBalance());
        installment.setPaymentId(installmentDto.getPaymentId());
        installment.setStatus(installmentDto.getStatus()); // Use paymentId

        return installment;
    }
}
