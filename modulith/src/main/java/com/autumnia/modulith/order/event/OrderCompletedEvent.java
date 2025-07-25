package com.autumnia.modulith.order.event;

public record OrderCompletedEvent(Long orderId, String productName, int quantity) {

}
