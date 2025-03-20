package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    // You can add custom query methods here if needed
}
