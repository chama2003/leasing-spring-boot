package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.InstallmentDto;
import com.example.leasing_backend.service.InstallmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/installment")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping
    public ResponseEntity<InstallmentDto> createInstallment(@RequestBody InstallmentDto installmentDto) {
        InstallmentDto savedInstallment = installmentService.createInstallment(installmentDto);
        return ResponseEntity.status(201).body(savedInstallment);
    }

    @GetMapping("{id}")
    public ResponseEntity<InstallmentDto> getInstallmentById(@PathVariable("id") Long id) {
        InstallmentDto installmentDto = installmentService.getInstallmentById(id);
        return ResponseEntity.ok(installmentDto);
    }

    @GetMapping
    public ResponseEntity<List<InstallmentDto>> getAllInstallments() {
        List<InstallmentDto> installments = installmentService.getAllInstallments();
        return ResponseEntity.ok(installments);
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<InstallmentDto>> getInstallmentsByPaymentId(@PathVariable("paymentId") Long paymentId) {
        List<InstallmentDto> installments = installmentService.getInstallmentsByPaymentId(paymentId);
        return ResponseEntity.ok(installments);
    }

    @PutMapping("{id}")
    public ResponseEntity<InstallmentDto> updateInstallmentById(@PathVariable("id") Long id, @RequestBody InstallmentDto updatedInstallment) {
        InstallmentDto installmentDto = installmentService.updateInstallmentById(id, updatedInstallment);
        return ResponseEntity.ok(installmentDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstallmentById(@PathVariable("id") Long id) {
        installmentService.deleteInstallmentById(id);
        return ResponseEntity.ok("Installment deleted successfully");
    }
}
