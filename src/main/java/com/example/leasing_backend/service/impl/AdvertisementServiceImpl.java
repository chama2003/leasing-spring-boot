package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.AdvertisementDto;
import com.example.leasing_backend.entity.Advertisement;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.AdvertisementMapper;
import com.example.leasing_backend.repository.AdvertisementRepository;
import com.example.leasing_backend.service.AdvertisementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public AdvertisementDto createAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = AdvertisementMapper.mapToAdvertisement(advertisementDto);
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return AdvertisementMapper.mapToAdvertisementDto(savedAdvertisement);
    }

    @Override
    public AdvertisementDto getAdvertisementById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found with id " + id));
        return new AdvertisementDto(advertisement);
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        return advertisements.stream()
                .map(AdvertisementMapper::mapToAdvertisementDto)
                .collect(Collectors.toList());
    }

    @Override
    public Advertisement updateAdvertisementById(Long id, Advertisement advertisement) {
        Advertisement existingAdvertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found with id " + id));

        existingAdvertisement.setTitle(advertisement.getTitle());
        existingAdvertisement.setDescription(advertisement.getDescription());
        existingAdvertisement.setImage(advertisement.getImage());

        return advertisementRepository.save(existingAdvertisement);
    }

    @Override
    public void deleteAdvertisementById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found with id " + id));
        advertisementRepository.deleteById(id);
    }
}
