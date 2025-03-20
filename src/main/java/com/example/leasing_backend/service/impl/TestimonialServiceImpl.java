package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.TestimonialDto;
import com.example.leasing_backend.entity.Testimonial;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.repository.TestimonialRepository;
import com.example.leasing_backend.service.TestimonialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @Override
    public TestimonialDto createTestimonial(TestimonialDto testimonialDto) {
        Testimonial testimonial = new Testimonial();
        testimonial.setName(testimonialDto.getName());
        testimonial.setMessage(testimonialDto.getMessage());
        testimonial.setImage(testimonialDto.getImage());

        Testimonial savedTestimonial = testimonialRepository.save(testimonial);
        return new TestimonialDto(savedTestimonial);
    }

    @Override
    public TestimonialDto getTestimonialById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial not found with id " + id));
        return new TestimonialDto(testimonial);
    }

    @Override
    public List<TestimonialDto> getAllTestimonials() {
        List<Testimonial> testimonials = testimonialRepository.findAll();
        return testimonials.stream()
                .map(TestimonialDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public TestimonialDto updateTestimonialById(Long id, TestimonialDto updatedTestimonial) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial not found with id " + id));

        testimonial.setName(updatedTestimonial.getName());
        testimonial.setMessage(updatedTestimonial.getMessage());
        testimonial.setImage(updatedTestimonial.getImage());

        Testimonial savedTestimonial = testimonialRepository.save(testimonial);
        return new TestimonialDto(savedTestimonial);
    }

    @Override
    public void deleteTestimonialById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial not found with id " + id));
        testimonialRepository.deleteById(id);
    }
}
