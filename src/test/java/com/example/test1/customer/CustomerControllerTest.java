package com.example.test1.customer;

import com.example.test1.controller.CustomerController;
import com.example.test1.model.Customer;
import com.example.test1.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void addCustomer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"Tom\",\n" +
                        "    \"email\": \"Tom@gmail.com\",\n" +
                        "    \"address\": \"Gdeto\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerById() throws Exception {
        // given
        long customerId = 1L;
        Customer customer = new Customer(customerId, "Tom", "Tom@gmail.com", "Gdeto");

        when(customerService.getCustomerById(customerId))
                .thenReturn(Optional.of(customer));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/customer")
                .param("customerId", String.valueOf(customerId)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Tom\",\n" +
                        "    \"email\": \"Tom@gmail.com\",\n" +
                        "    \"address\": \"Gdeto\"\n" +
                        "}"));
    }

    @Test
    void updateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/customer/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"aaaTom\",\n" +
                        "    \"email\": \"TTTTTom@gmail.com\",\n" +
                        "    \"address\": \"neGdeto\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomer() throws Exception {
        // given
        long customerId = 1L;

        // when/then
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer")
                .param("customerId", String.valueOf(customerId)))
                .andExpect(status().isNoContent());
    }
}