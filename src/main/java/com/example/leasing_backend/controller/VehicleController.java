package com.example.leasing_backend.controller;

import com.example.leasing_backend.entity.Vehicle;
import com.example.leasing_backend.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Vehicle")
@CrossOrigin("*")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Get all vehicle brands
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getAllBrands() {
        List<String> brands = vehicleService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    // Get models by selected brand
    @GetMapping("/models/{brand}")
    public ResponseEntity<List<String>> getModelsByBrand(@PathVariable("brand") String brand) {
        List<String> models = vehicleService.getModelsByBrand(brand);
        return ResponseEntity.ok(models);
    }

    // Get years by selected brand and model
    @GetMapping("/years/{brand}/{model}")
    public ResponseEntity<List<Integer>> getYearsByBrandAndModel(@PathVariable("brand") String brand,
                                                                 @PathVariable("model") String model) {
        List<Integer> years = vehicleService.getYearsByBrandAndModel(brand, model);
        return ResponseEntity.ok(years);
    }

    // Get estimated value by selected brand, model, and year
    @GetMapping("/estimatedValue/{brand}/{model}/{year}")
    public ResponseEntity<Float> getEstimatedValue(@PathVariable("brand") String brand,
                                                   @PathVariable("model") String model,
                                                   @PathVariable("year") Integer year) {
        Float estimatedValue = vehicleService.getEstimatedValue(brand, model, year);
        return ResponseEntity.ok(estimatedValue);
    }

    // Create a new vehicle (POST)
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(201).body(createdVehicle);
    }

    // Update an existing vehicle (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id,
                                                 @RequestBody Vehicle vehicleDetails) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
        return ResponseEntity.ok(updatedVehicle);
    }

    // Delete a vehicle (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
