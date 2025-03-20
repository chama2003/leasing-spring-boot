package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuarantorRepository extends JpaRepository<Guarantor, Long> {
}
