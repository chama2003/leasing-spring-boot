package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.InquiryDto;
import com.example.leasing_backend.entity.Inquiry;
import com.example.leasing_backend.exception.ResourceNotFoundException;

import com.example.leasing_backend.repository.InquiryRepository;
import com.example.leasing_backend.service.InquiryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryServiceImpl(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @Override
    public InquiryDto createInquiry(InquiryDto inquiryDto) {
        // Convert DTO to entity
        Inquiry inquiry = new Inquiry();
        inquiry.setSubject(inquiryDto.getSubject());
        inquiry.setName(inquiryDto.getName());
        inquiry.setContactNumber(inquiryDto.getContactNumber());
        inquiry.setEmail(inquiryDto.getEmail());

        // Save the inquiry to the database
        Inquiry savedInquiry = inquiryRepository.save(inquiry);
        return new InquiryDto(savedInquiry);
    }

    @Override
    public List<InquiryDto> getAllInquiries() {
        List<Inquiry> inquiries = inquiryRepository.findAll();
        return inquiries.stream()
                .map(InquiryDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public InquiryDto getInquiryById(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inquiry not found with id " + id));
        return new InquiryDto(inquiry);
    }

    @Override
    public void deleteInquiryById(Long id) {
        inquiryRepository.deleteById(id);
    }
}
