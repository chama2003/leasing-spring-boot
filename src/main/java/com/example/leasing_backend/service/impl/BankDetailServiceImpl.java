package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.BankDetailDto;
import com.example.leasing_backend.entity.BankDetail;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.BankDetailMapper;
import com.example.leasing_backend.repository.BankDetailRepository;
import com.example.leasing_backend.service.BankDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankDetailServiceImpl implements BankDetailService {

    private final BankDetailRepository bankDetailRepository;

    @Override
    @Transactional
    public BankDetailDto createBankDetail(BankDetailDto bankDetailDto) {
        BankDetail bankDetail = BankDetailMapper.mapToBankDetail(bankDetailDto);
        BankDetail savedBankDetail = bankDetailRepository.save(bankDetail);
        return BankDetailMapper.mapToBankDetailDto(savedBankDetail);
    }

    @Override
    @Transactional
    public BankDetailDto getBankDetailById(String accountNo) {
        BankDetail bankDetail = bankDetailRepository.findById(accountNo)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found with Account No: " + accountNo));
        return BankDetailMapper.mapToBankDetailDto(bankDetail);
    }

    @Override
    @Transactional
    public List<BankDetailDto> getAllBankDetails() {
        List<BankDetail> bankDetails = bankDetailRepository.findAll();
        return bankDetails.stream()
                .map(BankDetailMapper::mapToBankDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BankDetailDto updateBankDetailById(String accountNo, BankDetailDto updatedBankDetail) {
        BankDetail bankDetail = bankDetailRepository.findById(accountNo)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found with Account No: " + accountNo));

        bankDetail.setAccountType(updatedBankDetail.getAccountType());
        bankDetail.setBankName(updatedBankDetail.getBankName());
        bankDetail.setBranch(updatedBankDetail.getBranch());
        bankDetail.setBankbookFirstpage(updatedBankDetail.getBankbookFirstpage());

        BankDetail savedBankDetail = bankDetailRepository.save(bankDetail);
        return BankDetailMapper.mapToBankDetailDto(savedBankDetail);
    }

    @Override
    @Transactional
    public void deleteBankDetailById(String accountNo) {
        BankDetail bankDetail = bankDetailRepository.findById(accountNo)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found with Account No: " + accountNo));
        bankDetailRepository.deleteById(accountNo);
    }
}
