package com.example.test1.service;

import org.springframework.stereotype.Service;
import com.example.test1.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final List<Customer> customerList;
    private static long ID = 1;

    public CustomerService() {
        customerList = new ArrayList<>();
    }

    public void registerNewCustomer(Customer customer) {
        customer.setId(ID++);
        customerList.add(customer);
    }

    public Optional<Customer> getCustomerById(long customerId) {
        return customerList.stream()
                .filter(element -> element.getId() == customerId)
                .findFirst();
    }

    public boolean updateCustomer(Customer customer) {
        if (customerList.removeIf(c -> c.getId() == customer.getId())) {
            customerList.add(customer);
            return true;
        }
        return false;
    }

    public boolean removeCustomer(long customerId) {
        return customerList.removeIf(customer -> customer.getId() == customerId);
    }
}
