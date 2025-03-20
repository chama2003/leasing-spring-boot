package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.BankDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankDetailService {

    BankDetailDto createBankDetail(BankDetailDto bankDetailDto);

    BankDetailDto getBankDetailById(String accountNo);

    List<BankDetailDto> getAllBankDetails();

    BankDetailDto updateBankDetailById(String accountNo, BankDetailDto updatedBankDetail);

    void deleteBankDetailById(String accountNo);
}
