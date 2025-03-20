package com.example.leasing_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailDto {

    private String accountNo;  // Account Number
    private String accountType;  // Account Type
    private String bankName;  // Bank Name
    private String branch;  // Bank Branch
    private String bankbookFirstpage;  // Image of the first page of the bankbook
}
