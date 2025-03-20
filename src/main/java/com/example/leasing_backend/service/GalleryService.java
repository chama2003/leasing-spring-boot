package com.example.leasing_backend.service;

import com.example.leasing_backend.entity.Gallery;

import java.util.List;

public interface GalleryService {

    // Get all images in the gallery
    List<Gallery> getAllImages();

    // Add a new image to the gallery
    Gallery addImage(Gallery gallery);

    // Update an existing image in the gallery
    Gallery updateImage(Gallery gallery);

    // Delete an image by ID
    void deleteImage(Long id);

    // Get a gallery image by its ID
    Gallery getImageById(Long id);
}
