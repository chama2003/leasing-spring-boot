package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.TestimonialDto;
import com.example.leasing_backend.service.TestimonialService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin("*")
public class TestimonialController {

    private final TestimonialService testimonialService;
    private final StorageService storageService;

    // Constructor injection for testimonialService and storageService
    public TestimonialController(TestimonialService testimonialService, StorageService storageService) {
        this.testimonialService = testimonialService;
        this.storageService = storageService;
    }

    // Create Testimonial
    @PostMapping
    public ResponseEntity<TestimonialDto> createTestimonial(
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("message") String message) throws IOException {

        String fileName = storageService.uploadImage(file); // Upload the image via StorageService

        TestimonialDto testimonialDto = new TestimonialDto();
        testimonialDto.setName(name);
        testimonialDto.setMessage(message);
        testimonialDto.setImage(fileName); // Store the image filename in the database

        TestimonialDto savedTestimonial = testimonialService.createTestimonial(testimonialDto);
        return new ResponseEntity<>(savedTestimonial, HttpStatus.CREATED);
    }

    // Get Testimonial by ID
    @GetMapping("/{id}")
    public ResponseEntity<TestimonialDto> getTestimonialById(@PathVariable Long id) {
        TestimonialDto testimonialDto = testimonialService.getTestimonialById(id);
        return ResponseEntity.ok(testimonialDto);
    }

    // Get all Testimonials
    @GetMapping
    public ResponseEntity<List<TestimonialDto>> getAllTestimonials() {
        List<TestimonialDto> testimonialDtos = testimonialService.getAllTestimonials();
        return ResponseEntity.ok(testimonialDtos);
    }

    // Update Testimonial by ID
    @PutMapping("/{id}")
    public ResponseEntity<TestimonialDto> updateTestimonial(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("message") String message) throws IOException {

        String fileName = storageService.uploadImage(file); // Upload the new image

        TestimonialDto updatedTestimonial = new TestimonialDto();
        updatedTestimonial.setName(name);
        updatedTestimonial.setMessage(message);
        updatedTestimonial.setImage(fileName); // Update the image filename

        TestimonialDto savedTestimonial = testimonialService.updateTestimonialById(id, updatedTestimonial);
        return ResponseEntity.ok(savedTestimonial);
    }

    // Delete Testimonial by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestimonialById(@PathVariable Long id) {
        testimonialService.deleteTestimonialById(id);
        return ResponseEntity.ok("Testimonial deleted successfully");
    }

    // Download Testimonial Image
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
