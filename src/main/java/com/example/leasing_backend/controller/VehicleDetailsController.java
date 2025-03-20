package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.AdvertisementDto;
import com.example.leasing_backend.dto.VehicleDetailsDto;
import com.example.leasing_backend.entity.Advertisement;
import com.example.leasing_backend.entity.VehicleDetails;
import com.example.leasing_backend.service.AdvertisementService;
import com.example.leasing_backend.service.VehicleDetailsService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehicle")
public class VehicleDetailsController {

    private final VehicleDetailsService vehicleDetailsService;

    public VehicleDetailsController(VehicleDetailsService vehicleDetailsService, StorageService storageService) {
        this.vehicleDetailsService = vehicleDetailsService;
        this.storageService = storageService;
    }
    private final StorageService storageService;


    @PostMapping
    public ResponseEntity<VehicleDetailsDto> createVehicleDetails(@RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto savedVehicleDetails = vehicleDetailsService.createVehicleDetails(vehicleDetailsDto);
        return new ResponseEntity<>(savedVehicleDetails, HttpStatus.CREATED);
    }

    @GetMapping("{vehicleRegistrationNo}")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetailsById(@PathVariable("vehicleRegistrationNo") String vehicleRegistrationNo) {
        VehicleDetailsDto vehicleDetailsDto = vehicleDetailsService.getVehicleDetailsById(vehicleRegistrationNo);
        return ResponseEntity.ok(vehicleDetailsDto);
    }
    @PostMapping("/add")
    public ResponseEntity<VehicleDetailsDto> createVehicleDetail(
            @RequestParam("VehicleBookImageimage") MultipartFile VehicleBookImage,
            @RequestParam("VehicleFrontImgimage") MultipartFile VehicleFrontImg,
            @RequestParam("VehicleRearImgimage") MultipartFile VehicleRearImg,
            @RequestParam("VehicleRightImgimage") MultipartFile VehicleRightImg,
            @RequestParam("VehicleBrand") String VehicleBrand,
            @RequestParam("regino") String regino,
            @RequestParam("VehicleModel") String VehicleModel,
            @RequestParam("YearOfManufacture") int YearOfManufacture,
            @RequestParam("YearOfRegistration") int YearOfRegistration,
            @RequestParam("EngineNumber") String EngineNumber,
            @RequestParam("ChassiNumber") String ChassiNumber,
            @RequestParam("NumberOfOwner") int NumberOfOwner,
            @RequestParam("ExteriorColor") String ExteriorColor,
            @RequestParam("InteriorColor") String InteriorColor) throws IOException {

        // Upload image using StorageService
        String VehicleBookImage1 = storageService.uploadImage(VehicleBookImage);
        String VehicleFrontImg1 = storageService.uploadImage(VehicleFrontImg);
        String VehicleRearImg1 = storageService.uploadImage(VehicleRearImg);
        String VehicleRightImg1 = storageService.uploadImage(VehicleRightImg);

        // Create the VehicleDetailsDto and set all vehicle details
        VehicleDetailsDto vehicleDetails = new VehicleDetailsDto();
        vehicleDetails.setVehicleRegistrationNo(regino);
        vehicleDetails.setVehicleBrand(VehicleBrand);
        vehicleDetails.setVehicleModel(VehicleModel);
        vehicleDetails.setYearOfManufacture(YearOfManufacture);
        vehicleDetails.setYearOfRegistration(YearOfRegistration);
        vehicleDetails.setEngineNumber(EngineNumber);
        vehicleDetails.setChassiNumber(ChassiNumber);
        vehicleDetails.setNumberOfOwner(NumberOfOwner);
        vehicleDetails.setExteriorColor(ExteriorColor);
        vehicleDetails.setInteriorColor(InteriorColor);
        vehicleDetails.setVehicleBookImage(VehicleBookImage1);
        vehicleDetails.setVehicleFrontImg(VehicleFrontImg1);
        vehicleDetails.setVehicleRearImg(VehicleRearImg1);
        vehicleDetails.setVehicleRightImg(VehicleRightImg1);

        // Save the vehicle details and return the response
        VehicleDetailsDto savedVehicle = vehicleDetailsService.createVehicleDetails(vehicleDetails);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDetailsDto>> getAllVehicleDetails() {
        List<VehicleDetailsDto> vehicleDetails = vehicleDetailsService.getAllVehicleDetails();
        return ResponseEntity.ok(vehicleDetails);
    }

    @PutMapping("{vehicleRegistrationNo}")
    public ResponseEntity<VehicleDetailsDto> updateVehicleDetailsById(@PathVariable("vehicleRegistrationNo") String vehicleRegistrationNo,
                                                                      @RequestBody VehicleDetailsDto updatedVehicleDetails) {
        VehicleDetailsDto vehicleDetailsDto = vehicleDetailsService.updateVehicleDetailsById(vehicleRegistrationNo, updatedVehicleDetails);
        return ResponseEntity.ok(vehicleDetailsDto);
    }

    @DeleteMapping("{vehicleRegistrationNo}")
    public ResponseEntity<String> deleteVehicleDetailsById(@PathVariable("vehicleRegistrationNo") String vehicleRegistrationNo) {
        vehicleDetailsService.deleteVehicleDetailsById(vehicleRegistrationNo);
        return ResponseEntity.ok("Vehicle details deleted successfully");
    }


    // Download Vehicle Book Image
    @GetMapping("/download/vehicle-book/{fileName}")
    public ResponseEntity<byte[]> downloadVehicleBookImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download Vehicle Front Image
    @GetMapping("/download/vehicle-front/{fileName}")
    public ResponseEntity<byte[]> downloadVehicleFrontImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download Vehicle Rear Image
    @GetMapping("/download/vehicle-rear/{fileName}")
    public ResponseEntity<byte[]> downloadVehicleRearImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

    // Download Vehicle Right Image
    @GetMapping("/download/vehicle-right/{fileName}")
    public ResponseEntity<byte[]> downloadVehicleRightImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }

}
