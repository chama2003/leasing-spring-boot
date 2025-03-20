package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.PaymentDto;
import com.example.leasing_backend.entity.Payment;

public class PaymentMapper {

    public static PaymentDto mapToPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPaymentPlan(),
                payment.getAmountPaid(),
                payment.getTotalAmount(),
                payment.getStartDate(),
                payment.getInterestRate(),
                payment.getNumberOfInstallments(),
                payment.getCustomer()// Map customer username
        );
    }

    public static Payment mapToPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setId(paymentDto.getId());
        payment.setPaymentPlan(paymentDto.getPaymentPlan());
        payment.setAmountPaid(paymentDto.getAmountPaid());
        payment.setTotalAmount(paymentDto.getTotalAmount());
        payment.setStartDate(paymentDto.getStartDate());
        payment.setInterestRate(paymentDto.getInterestRate());
        payment.setNumberOfInstallments(paymentDto.getNumberOfInstallments());
payment.setCustomer(paymentDto.getCustomerUsername());
        // You can add logic to fetch the customer entity using the customer username, if necessary.
        return payment;
    }
}
