package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.VehicleDetailsDto;
import com.example.leasing_backend.entity.VehicleDetails;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.VehicleDetailsMapper;
import com.example.leasing_backend.repository.VehicleDetailsRepository;
import com.example.leasing_backend.service.VehicleDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleDetailsServiceImpl implements VehicleDetailsService {

    private final VehicleDetailsRepository vehicleDetailsRepository;

    @Override
    @Transactional
    public VehicleDetailsDto createVehicleDetails(VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetails vehicleDetails = VehicleDetailsMapper.mapToVehicleDetails(vehicleDetailsDto);
        VehicleDetails savedVehicleDetails = vehicleDetailsRepository.save(vehicleDetails);
        return VehicleDetailsMapper.mapToVehicleDetailsDto(savedVehicleDetails);
    }

    @Override
    @Transactional
    public VehicleDetailsDto getVehicleDetailsById(String vehicleRegistrationNo) {
        VehicleDetails vehicleDetails = vehicleDetailsRepository.findById(vehicleRegistrationNo)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with Registration No: " + vehicleRegistrationNo));
        return VehicleDetailsMapper.mapToVehicleDetailsDto(vehicleDetails);
    }

    @Override
    @Transactional
    public List<VehicleDetailsDto> getAllVehicleDetails() {
        List<VehicleDetails> vehicleDetailsList = vehicleDetailsRepository.findAll();
        return vehicleDetailsList.stream()
                .map(VehicleDetailsMapper::mapToVehicleDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleDetailsDto updateVehicleDetailsById(String vehicleRegistrationNo, VehicleDetailsDto updatedVehicleDetails) {
        VehicleDetails vehicleDetails = vehicleDetailsRepository.findById(vehicleRegistrationNo)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with Registration No: " + vehicleRegistrationNo));

        vehicleDetails.setVehicleBrand(updatedVehicleDetails.getVehicleBrand());
        vehicleDetails.setVehicleModel(updatedVehicleDetails.getVehicleModel());
        vehicleDetails.setYearOfManufacture(updatedVehicleDetails.getYearOfManufacture());
        vehicleDetails.setYearOfRegistration(updatedVehicleDetails.getYearOfRegistration());
        vehicleDetails.setEngineNumber(updatedVehicleDetails.getEngineNumber());
        vehicleDetails.setChassiNumber(updatedVehicleDetails.getChassiNumber());
        vehicleDetails.setNumberOfOwner(updatedVehicleDetails.getNumberOfOwner());
        vehicleDetails.setExteriorColor(updatedVehicleDetails.getExteriorColor());
        vehicleDetails.setInteriorColor(updatedVehicleDetails.getInteriorColor());
        vehicleDetails.setVehicleBookImage(updatedVehicleDetails.getVehicleBookImage());
        vehicleDetails.setVehicleFrontImg(updatedVehicleDetails.getVehicleFrontImg());
        vehicleDetails.setVehicleRearImg(updatedVehicleDetails.getVehicleRearImg());
        vehicleDetails.setVehicleRightImg(updatedVehicleDetails.getVehicleRightImg());

        VehicleDetails savedVehicleDetails = vehicleDetailsRepository.save(vehicleDetails);
        return VehicleDetailsMapper.mapToVehicleDetailsDto(savedVehicleDetails);
    }

    @Override
    @Transactional
    public void deleteVehicleDetailsById(String vehicleRegistrationNo) {
        VehicleDetails vehicleDetails = vehicleDetailsRepository.findById(vehicleRegistrationNo)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with Registration No: " + vehicleRegistrationNo));
        vehicleDetailsRepository.deleteById(vehicleRegistrationNo);
    }
}
