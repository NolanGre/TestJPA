package com.example.test1.customer;

import com.example.test1.model.Customer;
import com.example.test1.repo.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    /*
    *   This is a speculative case.
    *   These tests are created for improving skills.
    *
    *   Without using Mockito, we test our CustomerRepo for a subject of valid DB request.
    *   Of course, when we use standard JpaRepo method, there is no need of checking it.
    *   So I was forced to create a little bit weird method just to check it.
    */

    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckCaseWhenCustomerEmailDoesExist() {
        // given
        String email = "aboba@biba.com";
        Customer customer = new Customer(1, "aboba", email, "tuta");
        underTest.saveAndFlush(customer);

        // when
        boolean result = underTest.selectExistsEmail(email);

        // then
        assertThat(result).isTrue();

    }

    @Test
    void itShouldCheckCaseWhenCustomerEmailDoesNotExist() {
        // given
        String email = "aboba@biba.com";

        // when
        boolean result = underTest.selectExistsEmail(email);

        // then
        assertThat(result).isFalse();
    }
}
