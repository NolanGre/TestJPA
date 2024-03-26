package com.example.test1.service;

import org.springframework.stereotype.Service;
import com.example.test1.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final List<Order> orderList;
    private static long ID = 1;

    public OrderService() {
        orderList = new ArrayList<>();
    }

    public void registerNewOrder(Order order) {
        order.setId(ID++);
        orderList.add(order);
    }

    public Optional<Order> getOrderById(long orderId) {
        return orderList.stream()
                .filter(element -> element.getId() == orderId)
                .findFirst();
    }
}
