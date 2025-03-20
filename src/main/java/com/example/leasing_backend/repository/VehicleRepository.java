package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Get distinct vehicle brands
    @Query("SELECT DISTINCT v.make FROM Vehicle v")
    List<String> findAllBrands();

    // Get models based on selected brand
    @Query("SELECT DISTINCT v.model FROM Vehicle v WHERE v.make = :brand")
    List<String> findModelsByBrand(String brand);

    // Get manufacturing years based on selected brand and model
    @Query("SELECT DISTINCT v.manufacturingYear FROM Vehicle v WHERE v.make = :brand AND v.model = :model")
    List<Integer> findYearsByBrandAndModel(String brand, String model);

    // Get estimated value based on brand, model, and year
    @Query("SELECT v.estimatedValue FROM Vehicle v WHERE v.make = :brand AND v.model = :model AND v.manufacturingYear = :year")
    Float findEstimatedValueByBrandModelAndYear(String brand, String model, Integer year);
}
