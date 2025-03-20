package com.example.leasing_backend.dto;

import com.example.leasing_backend.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {

    private Long id;
    private String subject;
    private String name;
    private String contactNumber;
    private String email;

    // Constructor to convert from Inquiry entity
    public InquiryDto(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.subject = inquiry.getSubject();
        this.name = inquiry.getName();
        this.contactNumber = inquiry.getContactNumber();
        this.email = inquiry.getEmail();
    }
}
