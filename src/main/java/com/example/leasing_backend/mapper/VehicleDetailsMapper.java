package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.VehicleDetailsDto;
import com.example.leasing_backend.entity.VehicleDetails;

public class VehicleDetailsMapper {

    public static VehicleDetailsDto mapToVehicleDetailsDto(VehicleDetails vehicleDetails) {
        return new VehicleDetailsDto(
                vehicleDetails.getVehicleRegistrationNo(),
                vehicleDetails.getVehicleBrand(),
                vehicleDetails.getVehicleModel(),
                vehicleDetails.getYearOfManufacture(),
                vehicleDetails.getYearOfRegistration(),
                vehicleDetails.getEngineNumber(),
                vehicleDetails.getChassiNumber(),
                vehicleDetails.getNumberOfOwner(),
                vehicleDetails.getExteriorColor(),
                vehicleDetails.getInteriorColor(),
                vehicleDetails.getVehicleBookImage(),
                vehicleDetails.getVehicleFrontImg(),
                vehicleDetails.getVehicleRearImg(),
                vehicleDetails.getVehicleRightImg()
        );
    }

    public static VehicleDetails mapToVehicleDetails(VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setVehicleRegistrationNo(vehicleDetailsDto.getVehicleRegistrationNo());
        vehicleDetails.setVehicleBrand(vehicleDetailsDto.getVehicleBrand());
        vehicleDetails.setVehicleModel(vehicleDetailsDto.getVehicleModel());
        vehicleDetails.setYearOfManufacture(vehicleDetailsDto.getYearOfManufacture());
        vehicleDetails.setYearOfRegistration(vehicleDetailsDto.getYearOfRegistration());
        vehicleDetails.setEngineNumber(vehicleDetailsDto.getEngineNumber());
        vehicleDetails.setChassiNumber(vehicleDetailsDto.getChassiNumber());
        vehicleDetails.setNumberOfOwner(vehicleDetailsDto.getNumberOfOwner());
        vehicleDetails.setExteriorColor(vehicleDetailsDto.getExteriorColor());
        vehicleDetails.setInteriorColor(vehicleDetailsDto.getInteriorColor());
        vehicleDetails.setVehicleBookImage(vehicleDetailsDto.getVehicleBookImage());
        vehicleDetails.setVehicleFrontImg(vehicleDetailsDto.getVehicleFrontImg());
        vehicleDetails.setVehicleRearImg(vehicleDetailsDto.getVehicleRearImg());
        vehicleDetails.setVehicleRightImg(vehicleDetailsDto.getVehicleRightImg());
        return vehicleDetails;
    }
}
