package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.InstallmentDto;
import com.example.leasing_backend.entity.Installment;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.InstallmentMapper;
import com.example.leasing_backend.repository.InstallmentRepository;
import com.example.leasing_backend.service.InstallmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;

    public InstallmentServiceImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    public InstallmentDto createInstallment(InstallmentDto installmentDto) {
        Installment installment = InstallmentMapper.mapToInstallment(installmentDto);
        Installment savedInstallment = installmentRepository.save(installment);
        return InstallmentMapper.mapToInstallmentDto(savedInstallment);
    }

    @Override
    public InstallmentDto getInstallmentById(Long id) {
        Installment installment = installmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Installment not found with id " + id));
        return InstallmentMapper.mapToInstallmentDto(installment);
    }

    @Override
    public List<InstallmentDto> getAllInstallments() {
        List<Installment> installments = installmentRepository.findAll();
        return installments.stream()
                .map(InstallmentMapper::mapToInstallmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public InstallmentDto updateInstallmentById(Long id, InstallmentDto updatedInstallment) {
        Installment installment = installmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Installment not found with id " + id));

        installment.setPaymentAmount(updatedInstallment.getPaymentAmount());
        installment.setPaymentDate(updatedInstallment.getPaymentDate());
        installment.setRemainingBalance(updatedInstallment.getRemainingBalance());
        installment.setPaymentId(updatedInstallment.getPaymentId());
        installment.setStatus(updatedInstallment.getStatus());

        Installment savedInstallment = installmentRepository.save(installment);
        return InstallmentMapper.mapToInstallmentDto(savedInstallment);
    }

    @Override
    public void deleteInstallmentById(Long id) {
        Installment installment = installmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Installment not found with id " + id));
        installmentRepository.deleteById(id);
    }

    @Override
    public List<InstallmentDto> getInstallmentsByPaymentId(Long paymentId) {
        List<Installment> installments = installmentRepository.findByPaymentId(paymentId);
        if (installments.isEmpty()) {
            throw new ResourceNotFoundException("No installments found for paymentId " + paymentId);
        }
        return installments.stream()
                .map(InstallmentMapper::mapToInstallmentDto)
                .collect(Collectors.toList());
    }
}
