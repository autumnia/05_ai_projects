package com.autumnia.modulith.order;

import org.springframework.context.ApplicationEvent;

public record OrderCompletedEvent(Long orderId, String productName, int quantity) {

}
