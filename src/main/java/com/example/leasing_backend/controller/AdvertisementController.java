package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.AdvertisementDto;
import com.example.leasing_backend.entity.Advertisement;
import com.example.leasing_backend.mapper.AdvertisementMapper;
import com.example.leasing_backend.service.AdvertisementService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@CrossOrigin("*")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final StorageService storageService;

    public AdvertisementController(AdvertisementService advertisementService, StorageService storageService) {
        this.advertisementService = advertisementService;
        this.storageService = storageService;
    }

    // Create a new advertisement
    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(
            @RequestParam("image") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) throws IOException {

        // Upload image using StorageService
        String fileName = storageService.uploadImage(file);

        // Create advertisement entity
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisement.setImage(fileName); // Save the file path (or filename)

        // Convert entity to DTO
        AdvertisementDto advertisementDto = new AdvertisementDto(advertisement);

        AdvertisementDto savedAdvertisement = advertisementService.createAdvertisement(advertisementDto);
        return new ResponseEntity<>(savedAdvertisement, HttpStatus.CREATED);
    }

    // Edit an advertisement
    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) throws IOException {

        // Upload new image using StorageService
        String fileName = storageService.uploadImage(file);

        // Fetch the advertisement by ID
        AdvertisementDto advertisementDto = advertisementService.getAdvertisementById(id);

        // Update the fields
        advertisementDto.setTitle(title);
        advertisementDto.setDescription(description);
        advertisementDto.setImage(fileName); // Update the image path

        // Convert DTO to entity
        Advertisement advertisement = AdvertisementMapper.mapToAdvertisement(advertisementDto);

        // Update the advertisement
        Advertisement updatedAdvertisement = advertisementService.updateAdvertisementById(id, advertisement);

        // Convert the updated entity back to DTO
        AdvertisementDto updatedAdvertisementDto = new AdvertisementDto(updatedAdvertisement);

        return ResponseEntity.ok(updatedAdvertisementDto);
    }

    // Delete an advertisement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdvertisementById(@PathVariable Long id) {
        advertisementService.deleteAdvertisementById(id);
        return ResponseEntity.ok("Advertisement deleted successfully");
    }

    // Get advertisement by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable Long id) {
        AdvertisementDto advertisementDto = advertisementService.getAdvertisementById(id);
        return ResponseEntity.ok(advertisementDto);
    }

    // Get all advertisements
    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        List<AdvertisementDto> advertisementDtos = advertisementService.getAllAdvertisements();
        return ResponseEntity.ok(advertisementDtos);
    }

    // Download advertisement image by file name
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
