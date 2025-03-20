package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.InquiryDto;

import java.util.List;

public interface InquiryService {

    // Method to create a new inquiry
    InquiryDto createInquiry(InquiryDto inquiryDto);

    // Method to get all inquiries
    List<InquiryDto> getAllInquiries();

    // Method to get an inquiry by ID
    InquiryDto getInquiryById(Long id);

    // Method to delete an inquiry by ID
    void deleteInquiryById(Long id);
}
