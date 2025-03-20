package com.example.leasing_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_detail_of_applicant")
public class BankDetail {

    @Id
    @Column(name = "account_no", unique = true)
    private String accountNo;  // Account Number (Primary Key)

    @Column(name = "account_type")
    private String accountType;  // Type of account (e.g., Savings, Checking)

    @Column(name = "bank_name")
    private String bankName;  // Name of the bank

    @Column(name = "branch")
    private String branch;  // Branch of the bank

    @Column(name = "bankbook_firstpage")
    private String bankbookFirstpage;  // First page of the bank book image/link
}
