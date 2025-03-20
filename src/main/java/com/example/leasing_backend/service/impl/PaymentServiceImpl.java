package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.PaymentDto;
import com.example.leasing_backend.entity.Payment;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.PaymentMapper;
import com.example.leasing_backend.repository.PaymentRepository;
import com.example.leasing_backend.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.mapToPayment(paymentDto);
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.mapToPaymentDto(savedPayment);
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));
        return PaymentMapper.mapToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper::mapToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto updatePaymentById(Long id, PaymentDto updatedPayment) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));

        payment.setPaymentPlan(updatedPayment.getPaymentPlan());
        payment.setAmountPaid(updatedPayment.getAmountPaid());
        payment.setTotalAmount(updatedPayment.getTotalAmount());
        payment.setStartDate(updatedPayment.getStartDate());
        payment.setInterestRate(updatedPayment.getInterestRate());
        payment.setNumberOfInstallments(updatedPayment.getNumberOfInstallments());

        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.mapToPaymentDto(savedPayment);
    }

    @Override
    public void deletePaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> getPaymentsByCustomerUsername(String customerUsername) {
        List<Payment> payments = paymentRepository.findByCustomer(customerUsername);
        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("No payments found for customer with username " + customerUsername);
        }
        return payments.stream()
                .map(PaymentMapper::mapToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto updatePaymentByCustomerUsername(String customerUsername, PaymentDto updatedPayment) {
        List<Payment> payments = paymentRepository.findByCustomer(customerUsername);
        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("No payments found for customer with username " + customerUsername);
        }

        // Assuming you want to update the first payment for the customer.
        Payment payment = payments.get(0);
        payment.setPaymentPlan(updatedPayment.getPaymentPlan());
        payment.setAmountPaid(updatedPayment.getAmountPaid());
        payment.setTotalAmount(updatedPayment.getTotalAmount());
        payment.setStartDate(updatedPayment.getStartDate());
        payment.setInterestRate(updatedPayment.getInterestRate());
        payment.setNumberOfInstallments(updatedPayment.getNumberOfInstallments());

        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.mapToPaymentDto(savedPayment);
    }
}
