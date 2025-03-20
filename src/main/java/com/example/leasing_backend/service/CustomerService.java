package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerByUsername(String username);  // Changed from ID to Username

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomerByUsername(String username, CustomerDto updatedCustomer);  // Changed from ID to Username

    void deleteCustomerByUsername(String username);
    boolean verifyCustomerPassword(String username, String password);// Changed from ID to Username
}
