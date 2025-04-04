package com.autumnia.openai.config;

import com.autumnia.openai.basic.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class CommandLineRunnerHandler implements CommandLineRunner {
    private final ChatService chatService;

    @Override
    public void run(String... args) throws Exception {
        chatService.start_chat();
    }
}
