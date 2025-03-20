package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.AdvertisementDto;
import com.example.leasing_backend.entity.Advertisement;

public class AdvertisementMapper {

    // Convert Advertisement entity to AdvertisementDto
    public static AdvertisementDto mapToAdvertisementDto(Advertisement advertisement) {
        return new AdvertisementDto(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getDescription(),
                advertisement.getImage()
        );
    }

    // Convert AdvertisementDto to Advertisement entity
    public static Advertisement mapToAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementDto.getId());
        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setDescription(advertisementDto.getDescription());
        advertisement.setImage(advertisementDto.getImage());
        return advertisement;
    }

}
