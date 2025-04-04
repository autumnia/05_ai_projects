package com.autumnia.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Value("classpath:prompt.txt")
    private Resource resource ;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    // 설정한 openai key를 읽어 들인다
    public ChatClient chatClient(ChatClient.Builder chatClient_builder) {
        return chatClient_builder
                .defaultSystem(resource)
                .build();
    }


//    @Bean
//    // 설정한 openai key를 읽어 들인다
//    public ChatClient chatClient(ChatClient.Builder chatClient_builder) {
//        return chatClient_builder
//                .defaultAdvisors( new MessageChatMemoryAdvisor( new InMemoryChatMemory() ) )
//                .build();
//
//    }
}
