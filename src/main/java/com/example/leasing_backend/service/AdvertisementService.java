package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.AdvertisementDto;
import com.example.leasing_backend.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDto createAdvertisement(AdvertisementDto advertisementDto);
    AdvertisementDto getAdvertisementById(Long id);
    List<AdvertisementDto> getAllAdvertisements();
    Advertisement updateAdvertisementById(Long id, Advertisement advertisement);
    void deleteAdvertisementById(Long id);
}
