package com.example.test1.order;

import com.example.test1.model.Order;
import com.example.test1.repo.OrderRepository;
import com.example.test1.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService underTest;

    @Test
    void registerNewOrder() {
        // given
        Order order = new Order(1L, 1L, 1L, 20, 453.23, "status_kaif");

        // when
        underTest.registerNewOrder(order);

        // then
        verify(orderRepository).saveAndFlush(order);
    }

    @Test
    void getOrderById() {
        // given
        long id = 1L;

        // when
        underTest.getOrderById(id);

        // then
        verify(orderRepository).findById(id);
        ;

    }
}