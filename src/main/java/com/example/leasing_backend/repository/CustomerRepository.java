package com.example.leasing_backend.repository;

import com.example.leasing_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> { // Use String as the ID type

    Optional<Customer> findByUsername(String username);  // Find by username
}
