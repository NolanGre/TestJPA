package com.example.test1.order;

import com.example.test1.controller.OrderController;
import com.example.test1.model.Order;
import com.example.test1.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void placeOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/order/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"customerId\": 2,\n" +
                        "    \"productId\": 6,\n" +
                        "    \"quantity\": 14,\n" +
                        "    \"totalPrice\": 29.19,\n" +
                        "    \"status\": \"actual\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrder() throws Exception {
        // given
        long orderId = 1L;
        Order order = new Order(orderId, 2L, 6L, 14, 29.19, "actual");
        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(order));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/order")
                .param("orderId", String.valueOf(orderId)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"customerId\": 2,\n" +
                        "    \"productId\": 6,\n" +
                        "    \"quantity\": 14,\n" +
                        "    \"totalPrice\": 29.19,\n" +
                        "    \"status\": \"actual\"\n" +
                        "}"));
    }
}