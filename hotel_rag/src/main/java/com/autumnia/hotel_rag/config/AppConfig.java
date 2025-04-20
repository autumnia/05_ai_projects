package com.autumnia.hotel_rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    // 설정한 openai key를 읽어 들인다
    public ChatClient chatClient(ChatClient.Builder chatClient_builder) {
        return chatClient_builder
                .build();
    }
}