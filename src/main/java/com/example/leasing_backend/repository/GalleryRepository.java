package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    // Additional query methods can be defined here
}
