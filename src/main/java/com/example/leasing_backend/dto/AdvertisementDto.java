package com.example.leasing_backend.dto;

import com.example.leasing_backend.entity.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    private Long id;
    private String title;
    private String description;
    private String image;

    // Constructor to convert from Advertisement entity
    public AdvertisementDto(Advertisement advertisement) {
        this.id = advertisement.getId();
        this.title = advertisement.getTitle();
        this.description = advertisement.getDescription();
        this.image = advertisement.getImage();
    }
}
