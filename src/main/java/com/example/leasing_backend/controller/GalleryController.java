package com.example.leasing_backend.controller;

import com.example.leasing_backend.entity.Gallery;
import com.example.leasing_backend.service.GalleryService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin("*")
public class GalleryController {

    private final GalleryService galleryService;
    private final StorageService storageService;

    // Constructor injection for galleryService and storageService
    public GalleryController(GalleryService galleryService, StorageService storageService) {
        this.galleryService = galleryService;
        this.storageService = storageService;
    }

    // Get all images
    @GetMapping
    public ResponseEntity<List<Gallery>> getAllImages() {
        List<Gallery> images = galleryService.getAllImages();
        return ResponseEntity.ok(images);
    }

    // Add new image
    @PostMapping
    public ResponseEntity<Gallery> addImage(@RequestParam("image") MultipartFile file) throws IOException {
        String fileName = storageService.uploadImage(file); // Upload image using StorageService

        Gallery gallery = new Gallery();
        gallery.setImage(fileName); // Save the image file name (path) in the database
        Gallery savedGallery = galleryService.addImage(gallery);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGallery);
    }

    // Edit image
    @PutMapping("/{id}")
    public ResponseEntity<?> editImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) throws IOException {
        // Check if the image exists before editing
        Gallery gallery = galleryService.getImageById(id);

        if (gallery == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found with id: " + id);
        }

        String fileName = storageService.uploadImage(file); // Upload new image
        gallery.setImage(fileName); // Update image path
        Gallery updatedGallery = galleryService.updateImage(gallery);

        return ResponseEntity.ok(updatedGallery);
    }

    // Delete an image by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        galleryService.deleteImage(id);
        return ResponseEntity.ok("Image deleted successfully");
    }

    // Download an image by file name
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
