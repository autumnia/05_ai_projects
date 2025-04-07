package com.autumnia.rag.movierag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    // 설정한 openai key를 읽어 들인다
    public ChatClient chatClient(ChatClient.Builder chatClient_builder) {
        return chatClient_builder
                .build();
    }
}
