package com.example.test1.service;

import com.example.test1.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test1.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerNewCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    public Optional<Customer> getCustomerById(long customerId) {
        return customerRepository.findById(customerId);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    public void removeCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
