package com.example.leasing_backend.dto;

import com.example.leasing_backend.entity.Testimonial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialDto {

    private Long id;
    private String name;
    private String message;
    private String image;

    public TestimonialDto(Testimonial testimonial) {
        this.id = testimonial.getId();
        this.name = testimonial.getName();
        this.message = testimonial.getMessage();
        this.image = testimonial.getImage();
    }
}
