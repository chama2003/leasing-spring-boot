package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.entity.Gallery;
import com.example.leasing_backend.repository.GalleryRepository;
import com.example.leasing_backend.service.GalleryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    // Constructor injection for galleryRepository
    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery addImage(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery updateImage(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public void deleteImage(Long id) {
        galleryRepository.deleteById(id);
    }

    @Override
    public Gallery getImageById(Long id) {
        return galleryRepository.findById(id).orElse(null); // Return null if not found
    }
}
