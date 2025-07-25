package com.autumnia.modulith.payment.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(Long orderId) {
        // PG사 통신 필요
        
        System.out.printf("결제 처리 완료: 주문번호 %d\n", orderId);
        System.out.println("결제 완료\n");
    }
}