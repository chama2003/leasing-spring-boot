package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.BankDetailDto;
import com.example.leasing_backend.entity.BankDetail;

public class BankDetailMapper {

    public static BankDetailDto mapToBankDetailDto(BankDetail bankDetail) {
        return new BankDetailDto(
                bankDetail.getAccountNo(),
                bankDetail.getAccountType(),
                bankDetail.getBankName(),
                bankDetail.getBranch(),
                bankDetail.getBankbookFirstpage()
        );
    }

    public static BankDetail mapToBankDetail(BankDetailDto bankDetailDto) {
        BankDetail bankDetail = new BankDetail();
        bankDetail.setAccountNo(bankDetailDto.getAccountNo());
        bankDetail.setAccountType(bankDetailDto.getAccountType());
        bankDetail.setBankName(bankDetailDto.getBankName());
        bankDetail.setBranch(bankDetailDto.getBranch());
        bankDetail.setBankbookFirstpage(bankDetailDto.getBankbookFirstpage());
        return bankDetail;
    }
}
