package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.InquiryDto;
import com.example.leasing_backend.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@CrossOrigin("*")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // Create a new inquiry
    @PostMapping
    public ResponseEntity<InquiryDto> createInquiry(@RequestBody InquiryDto inquiryDto) {
        InquiryDto savedInquiry = inquiryService.createInquiry(inquiryDto);
        return new ResponseEntity<>(savedInquiry, HttpStatus.CREATED);
    }

    // Get all inquiries
    @GetMapping
    public ResponseEntity<List<InquiryDto>> getAllInquiries() {
        List<InquiryDto> inquiries = inquiryService.getAllInquiries();
        return ResponseEntity.ok(inquiries);
    }

    // Get an inquiry by ID
    @GetMapping("/{id}")
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable Long id) {
        InquiryDto inquiryDto = inquiryService.getInquiryById(id);
        return ResponseEntity.ok(inquiryDto);
    }

    // Delete an inquiry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInquiryById(@PathVariable Long id) {
        inquiryService.deleteInquiryById(id);
        return ResponseEntity.ok("Inquiry deleted successfully");
    }
}
