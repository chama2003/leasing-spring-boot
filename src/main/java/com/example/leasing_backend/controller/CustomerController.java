package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.CustomerDto;
import com.example.leasing_backend.service.CustomerService;
import com.example.leasing_backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Adjust as needed for your frontend port
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomer = customerService.createCustomer(customerDto);
        System.out.println(savedCustomer.getUsername());
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("{username}")
    public ResponseEntity<CustomerDto> getCustomerByUsername(@PathVariable("username") String username) {
        CustomerDto customerDto = customerService.getCustomerByUsername(username);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("{username}")
    public ResponseEntity<CustomerDto> updateCustomerByUsername(@PathVariable("username") String username,
                                                                @RequestBody CustomerDto updatedCustomer) {
        CustomerDto customerDto = customerService.updateCustomerByUsername(username, updatedCustomer);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteCustomerByUsername(@PathVariable("username") String username) {
        customerService.deleteCustomerByUsername(username);
        return ResponseEntity.ok("Customer deleted successfully");
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyCustomerPassword(@RequestParam String username, @RequestParam String password) {
        boolean isPasswordCorrect = customerService.verifyCustomerPassword(username,password );
        if (isPasswordCorrect) {
            return ResponseEntity.ok("Password is correct");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }
}
