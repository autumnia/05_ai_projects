package com.autumnia.modulith.inventory.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public void reduceStock(String productName, int quantity) {
        System.out.printf("재고 감소: %s x %d\n", productName, quantity);
    }
}