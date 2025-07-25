package com.autumnia.modulith.payment.event;


import com.autumnia.modulith.order.event.OrderCompletedEvent;
import com.autumnia.modulith.payment.service.PaymentService;
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