package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(String userId);
    boolean existsByNic(Long nic);
}
