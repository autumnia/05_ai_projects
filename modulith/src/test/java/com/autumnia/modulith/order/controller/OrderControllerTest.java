package com.autumnia.modulith.order.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {
    @LocalServerPort
    private int port;

    @Test
    public void order_should_respond_under_1second() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + port + "/api/order";
//        LoginRequest request = new LoginRequest("admin", "password");

        long start = System.currentTimeMillis();
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl,  String.class);
//        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        long end = System.currentTimeMillis();

        long duration = end - start;

        assertTrue(duration < 1000, "Order API should respond in under 1 second");
    }

}