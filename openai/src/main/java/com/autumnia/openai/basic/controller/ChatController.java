package com.autumnia.openai.basic.controller;

import com.autumnia.openai.basic.domain.Answer;
import com.autumnia.openai.basic.domain.Movie;
import com.autumnia.openai.basic.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat")
    public String getChat(@RequestParam String message) {
        return chatService.getChat(message);
    }

    @GetMapping("/chat_message")
    public String get_chatmessage(@RequestParam String message) {
        return chatService.get_chatmessage(message);
    }

    // http://localhost:8080/ai/chat_placeholder?subject=java&tone=women&message=자바클래스에 대해서 설명해줘
    @GetMapping("/chat_placeholder")
    public String chat_placeholder(@RequestBody RequestRcd request) {
        return chatService.chat_placeholder(request);
    }

    @GetMapping("/chatjson")
    public ChatResponse get_chatjson(@RequestBody RequestRcd request) {
        return chatService.get_chatjson( request );
    }

    @GetMapping("/chatdto")
    public Answer get_object(@RequestBody RequestRcd request) {
        return chatService.get_chatdto( request );
    }

    @GetMapping("/chatlist")
    public List<String> get_list(@RequestBody RequestRcd request) {
        return chatService.get_list(request);
    }

    @GetMapping("/chatmap")
    public Map<String, String> get_map(@RequestBody RequestRcd request) {
        return chatService.get_map(request);
    }

    @GetMapping("/chatobject")
    public List<Movie> get_map(@RequestBody RequestMovie request) {
        return chatService.get_object(request);
    }
}
