package com.autumnia.modulith.payment;


import com.autumnia.modulith.order.OrderCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    private final PaymentService paymentService;

    public PaymentEventListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Async
    @EventListener
    void handleOrderCompleted(OrderCompletedEvent event) {
        paymentService.processPayment(event.orderId());
    }
}