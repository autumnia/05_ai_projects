package com.autumnia.modulith;

import com.autumnia.modulith.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModulithApplicationTests {

	@Autowired
	OrderService orderService;

	@Test
	void contextLoads() {
	}

	@Test
	void test_place_order() {
		orderService.placeOrder("맥북", 2);
	}

}
