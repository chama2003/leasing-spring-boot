package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Staff;
import com.example.leasing_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    // You can add custom query methods here if needed
}
