package com.example.leasing_backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private static final String UPLOAD_DIR = "C:\\Users\\chama\\Desktop\\upload";

    // Method to upload an image
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent()); // Ensure the directory exists
        Files.write(path, file.getBytes()); // Save file
        return fileName; // Returning the filename
    }

    // Method to download an image
    // In your StorageService (backend code)
    public byte[] downloadImage(String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + fileName);
        byte[] imageData = Files.readAllBytes(path);
        return imageData;
    }

}
