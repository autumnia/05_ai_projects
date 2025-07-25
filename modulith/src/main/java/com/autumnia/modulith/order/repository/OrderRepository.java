package com.autumnia.modulith.order.repository;

import com.autumnia.modulith.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}