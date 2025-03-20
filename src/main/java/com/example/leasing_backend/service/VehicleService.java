package com.example.leasing_backend.service;

import com.example.leasing_backend.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<String> getAllBrands();

    List<String> getModelsByBrand(String brand);

    List<Integer> getYearsByBrandAndModel(String brand, String model);

    Float getEstimatedValue(String brand, String model, Integer year);

    // CRUD operations
    Vehicle createVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Long id, Vehicle vehicleDetails);

    void deleteVehicle(Long id);
}
