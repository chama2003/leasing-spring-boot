package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    // Custom query methods (if needed) can be added here
}
