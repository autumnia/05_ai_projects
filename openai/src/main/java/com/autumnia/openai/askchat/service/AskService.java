package com.autumnia.openai.askchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskService {
    private final ChatModel chatModel;

    public String get_response(String msg) {
        return chatModel.call(msg);
    }

    public String get_response_with_options(String msg) {
        ChatResponse chatResponse = chatModel.call(new Prompt(
            msg,
            OpenAiChatOptions.builder()
                .model("gpt-4")
                .temperature(0.8)
                .build()
        ));

        return chatResponse.getResult()
                .getOutput()
                .getText();
    }
}
