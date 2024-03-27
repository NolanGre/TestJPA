package com.example.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.test1.model.Customer;
import com.example.test1.service.CustomerService;

import java.util.Optional;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.registerNewCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerById(@RequestParam(name = "customerId") long customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);

        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateProduct(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Void> deleteCustomer(@RequestParam(name = "customerId") long customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
