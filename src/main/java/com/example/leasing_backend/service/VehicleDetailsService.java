package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.VehicleDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleDetailsService {

    VehicleDetailsDto createVehicleDetails(VehicleDetailsDto vehicleDetailsDto);

    VehicleDetailsDto getVehicleDetailsById(String vehicleRegistrationNo);

    List<VehicleDetailsDto> getAllVehicleDetails();

    VehicleDetailsDto updateVehicleDetailsById(String vehicleRegistrationNo, VehicleDetailsDto updatedVehicleDetails);

    void deleteVehicleDetailsById(String vehicleRegistrationNo);
}
