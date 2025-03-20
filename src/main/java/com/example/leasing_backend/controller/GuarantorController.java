package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.GuarantorDto;
import com.example.leasing_backend.dto.TestimonialDto;
import com.example.leasing_backend.service.GuarantorService;
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
@RequestMapping("/api/guarantor")
public class GuarantorController {

    private final GuarantorService guarantorService;
    private final StorageService storageService;

    public GuarantorController(GuarantorService guarantorService, StorageService storageService) {
        this.guarantorService = guarantorService;
        this.storageService = storageService;
    }

    // Create Guarantor (with file upload)
    @PostMapping
    public ResponseEntity<GuarantorDto> createGuarantor(
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String guarantorsName,
            @RequestParam("occupation") String guarantorOccupation,
            @RequestParam("nic") Long nic,
            @RequestParam("income") double guarantorIncome) throws IOException {

        // Upload the image via StorageService
        String fileName = storageService.uploadImage(file);

        // Create and populate the GuarantorDto
        GuarantorDto guarantorDto = new GuarantorDto();
        guarantorDto.setGuarantorNicImg(fileName); // Store the image filename in the database
        guarantorDto.setGuarantorsName(guarantorsName);
        guarantorDto.setGuarantorNic(nic);
        guarantorDto.setGuarantorOccupation(guarantorOccupation);
        guarantorDto.setGuarantorIncome(guarantorIncome);

        // Save the GuarantorDto and return the response
        GuarantorDto savedGuarantor = guarantorService.createGuarantor(guarantorDto);
        return new ResponseEntity<>(savedGuarantor, HttpStatus.CREATED);
    }

    // Get Guarantor by NIC
    @GetMapping("{guarantorNic}")
    public ResponseEntity<GuarantorDto> getGuarantorById(@PathVariable("guarantorNic") Long guarantorNic) {
        GuarantorDto guarantorDto = guarantorService.getGuarantorById(guarantorNic);
        return ResponseEntity.ok(guarantorDto);
    }

    // Get all Guarantors
    @GetMapping
    public ResponseEntity<List<GuarantorDto>> getAllGuarantors() {
        List<GuarantorDto> guarantors = guarantorService.getAllGuarantors();
        return ResponseEntity.ok(guarantors);
    }

    // Update Guarantor by NIC
    @PutMapping("{guarantorNic}")
    public ResponseEntity<GuarantorDto> updateGuarantorById(@PathVariable("guarantorNic") Long guarantorNic,
                                                            @RequestBody GuarantorDto updatedGuarantor) {
        GuarantorDto guarantorDto = guarantorService.updateGuarantorById(guarantorNic, updatedGuarantor);
        return ResponseEntity.ok(guarantorDto);
    }

    // Delete Guarantor by NIC
    @DeleteMapping("{guarantorNic}")
    public ResponseEntity<String> deleteGuarantorById(@PathVariable("guarantorNic") Long guarantorNic) {
        guarantorService.deleteGuarantorById(guarantorNic);
        return ResponseEntity.ok("Guarantor deleted successfully");
    }

    // Download Image (Guarantor NIC Image)
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
