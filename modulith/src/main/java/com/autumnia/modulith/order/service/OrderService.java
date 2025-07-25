package com.autumnia.modulith.order.service;

import com.autumnia.modulith.order.Order;
import com.autumnia.modulith.order.event.OrderCompletedEvent;
import com.autumnia.modulith.order.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher events;

    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher events) {
        this.orderRepository = orderRepository;
        this.events = events;
    }

    public Order placeOrder(String productName, int quantity) {
        System.out.println("주문 시작");

        System.out.printf("주문 내용: %s x %d\n", productName, quantity);

        Order order = new Order(productName, quantity);
        orderRepository.save(order);

        events.publishEvent(new OrderCompletedEvent(order.getId(), productName, quantity));

        return order;
    }
}