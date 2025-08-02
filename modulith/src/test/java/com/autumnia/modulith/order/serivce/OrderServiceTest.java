package com.autumnia.modulith.order.serivce;

import com.autumnia.modulith.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Test
    void test_place_order() {
        orderService.placeOrder("맥북", 2);
    }
}