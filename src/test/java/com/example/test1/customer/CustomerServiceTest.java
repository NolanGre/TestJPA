package com.example.test1.customer;

import com.example.test1.model.Customer;
import com.example.test1.repo.CustomerRepository;
import com.example.test1.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/*
* 1. When you create mocks in your test methods, Mockito tracks these mocks internally.
*
* 2. Before each test method runs, Mockito automatically registers these
*    mocks for cleanup by wrapping them in an AutoCloseable instance.
*
* 3. After each test method completes, Mockito automatically closes or resets these mocks,
*    ensuring they are ready for the next test method.
*/
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService underTest;

    /*
    *   All this checks absolutely useless.
    *   Even that, I write this code to make a basic Mockito verifying
    *
    *   For future me)
    *   Guess in mocking first of all, I need to look at the testing class.
    *
    *   First step - GIVE all information that pass to checking method.
    *   Second step - give condition WHEN (in other words, call checking method)
    *   Third step - VERIFY behavior or result.
    *       underChecking object call mocked-object method. That means we need to verify this call.
    *
    *   In this primitive app, there is no requirement to check direct call.
    */

    @Test
    void checkRegisterNewCustomer() {
        // given
        Customer customer = new Customer(1, "aboba", "aboba@biba.com", "tuta");

        // when
        underTest.registerNewCustomer(customer);

        // then
        verify(customerRepository).saveAndFlush(customer);
    }

    @Test
    void checkGettingCustomerById() {
        // given
        long customerId = 1L;

        // when
        underTest.getCustomerById(customerId);

        // then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void checkUpdateCustomer() {
        // given
        Customer customer = new Customer(1, "aboba", "aboba@biba.com", "tuta");

        //when
        underTest.updateCustomer(customer);

        // then
        verify(customerRepository).saveAndFlush(customer);
    }

    @Test
    void removeCustomer() {
        // given
        long customerId = 1L;

        // when
        underTest.removeCustomer(customerId);

        //then
        verify(customerRepository).deleteById(customerId);

    }
}