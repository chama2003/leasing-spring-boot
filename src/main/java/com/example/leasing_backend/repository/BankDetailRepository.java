package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailRepository extends JpaRepository<BankDetail, String> {
}
