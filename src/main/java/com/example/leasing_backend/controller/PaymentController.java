package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.PaymentDto;
import com.example.leasing_backend.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto savedPayment = paymentService.createPayment(paymentDto);
        System.out.println(savedPayment.getPaymentPlan());
        System.out.println(savedPayment.getAmountPaid());
        System.out.println(savedPayment.getTotalAmount());
        System.out.println(savedPayment.getStartDate());
        System.out.println(savedPayment.getInterestRate());
        System.out.println(savedPayment.getNumberOfInstallments());
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Long id) {
        PaymentDto paymentDto = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/customer/{customerUsername}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByCustomerUsername(@PathVariable("customerUsername") String customerUsername) {
        List<PaymentDto> payments = paymentService.getPaymentsByCustomerUsername(customerUsername);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/customer/{customerUsername}")
    public ResponseEntity<PaymentDto> updatePaymentByCustomerUsername(@PathVariable("customerUsername") String customerUsername,
                                                                      @RequestBody PaymentDto updatedPayment) {
        PaymentDto paymentDto = paymentService.updatePaymentByCustomerUsername(customerUsername, updatedPayment);
        return ResponseEntity.ok(paymentDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PaymentDto> updatePaymentById(@PathVariable("id") Long id, @RequestBody PaymentDto updatedPayment) {
        System.out.println(updatedPayment.getAmountPaid());
        PaymentDto paymentDto = paymentService.updatePaymentById(id, updatedPayment);
        return ResponseEntity.ok(paymentDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable("id") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }
}
