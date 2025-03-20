package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    // Custom queries can be added here if needed
}
