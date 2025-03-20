package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.TestimonialDto;
import java.util.List;

public interface TestimonialService {

    TestimonialDto createTestimonial(TestimonialDto testimonialDto);

    TestimonialDto getTestimonialById(Long id);

    List<TestimonialDto> getAllTestimonials();

    TestimonialDto updateTestimonialById(Long id, TestimonialDto updatedTestimonial);

    void deleteTestimonialById(Long id);
}
