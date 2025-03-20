package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.CustomerDto;
import com.example.leasing_backend.entity.Customer;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.hash.PasswordUtils;
import com.example.leasing_backend.mapper.CustomerMapper;
import com.example.leasing_backend.repository.CustomerRepository;
import com.example.leasing_backend.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        // Hash the password before saving
        String hashedPassword = PasswordUtils.hashPassword(customerDto.getPassword());
        customerDto.setPassword(hashedPassword);

        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    @Transactional
    public CustomerDto getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username: " + username));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    @Transactional
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto updateCustomerByUsername(String username, CustomerDto updatedCustomer) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username: " + username));

        customer.setPassword(PasswordUtils.hashPassword(updatedCustomer.getPassword())); // Hash the new password
        customer.setUser_username(updatedCustomer.getUser_username());
        customer.setRole(updatedCustomer.getRole());

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username: " + username));
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public boolean verifyCustomerPassword(String username, String password) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username: " + username));

        // Verify the entered password against the stored hashed password
        return PasswordUtils.verifyPassword(password, customer.getPassword());
    }
}
