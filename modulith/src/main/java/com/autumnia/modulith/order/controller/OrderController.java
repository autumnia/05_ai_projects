package com.autumnia.modulith.order.controller;

import com.autumnia.modulith.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    @ResponseBody
    public String place_order() {
        orderService.placeOrder("Mac book", 3);

        return "order";
    }

}
