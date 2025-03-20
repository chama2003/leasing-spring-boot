package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.CustomerDto;
import com.example.leasing_backend.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;  // Prevent null pointer exception
        }
        return new CustomerDto(
                customer.getUsername(),
                customer.getPassword(),
                customer.getUser_username(),
                customer.getRole()
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;  // Prevent null pointer exception
        }
        Customer customer = new Customer();
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setUser_username(customerDto.getUser_username());
        customer.setRole(customerDto.getRole());
        // adminId is set automatically to 1
        return customer;
    }
}
