package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, String> {
}
