package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.entity.Vehicle;
import com.example.leasing_backend.repository.VehicleRepository;
import com.example.leasing_backend.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<String> getAllBrands() {
        return vehicleRepository.findAllBrands();
    }

    @Override
    public List<String> getModelsByBrand(String brand) {
        return vehicleRepository.findModelsByBrand(brand);
    }

    @Override
    public List<Integer> getYearsByBrandAndModel(String brand, String model) {
        return vehicleRepository.findYearsByBrandAndModel(brand, model);
    }

    @Override
    public Float getEstimatedValue(String brand, String model, Integer year) {
        return vehicleRepository.findEstimatedValueByBrandModelAndYear(brand, model, year);
    }

    // Create a new vehicle
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Update an existing vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));

        existingVehicle.setMake(vehicleDetails.getMake());
        existingVehicle.setModel(vehicleDetails.getModel());
        existingVehicle.setManufacturingYear(vehicleDetails.getManufacturingYear());
        existingVehicle.setEstimatedValue(vehicleDetails.getEstimatedValue());

        return vehicleRepository.save(existingVehicle);
    }

    // Delete a vehicle
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));
        vehicleRepository.delete(vehicle);
    }
}
