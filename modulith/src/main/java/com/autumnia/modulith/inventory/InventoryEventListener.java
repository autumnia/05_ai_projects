package com.autumnia.modulith.inventory;


import com.autumnia.modulith.order.event.OrderCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventListener {

    private final InventoryService inventoryService;

    public InventoryEventListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Async
    @EventListener
    void handleOrderCompleted(OrderCompletedEvent event) {
        inventoryService.reduceStock(event.productName(), event.quantity());
    }
}