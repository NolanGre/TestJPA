package com.example.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.test1.model.Order;
import com.example.test1.service.OrderService;

import java.util.Optional;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        orderService.registerNewOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/order")
    public ResponseEntity<Order> getOrder(@RequestParam(name="orderId") long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);

        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
