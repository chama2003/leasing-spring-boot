package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.VehicleDto;
import com.example.leasing_backend.entity.Vehicle;

public class VehicleMapper {

    public static VehicleDto mapToVehicleDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getManufacturingYear(),
                vehicle.getEstimatedValue()
        );
    }

    public static Vehicle mapToVehicle(VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getId(),
                vehicleDto.getMake(),
                vehicleDto.getModel(),
                vehicleDto.getManufacturingYear(),
                vehicleDto.getEstimatedValue()
        );
    }
}
