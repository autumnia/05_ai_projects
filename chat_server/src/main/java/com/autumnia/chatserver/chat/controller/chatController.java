package com.autumnia.chatserver.chat.controller;

import com.autumnia.chatserver.chat.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="chat API", description="V1 chat API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class chatController {
    private final ChatService chatService;

    @Operation(
            summary = "채팅리스트를 가져옵니다.",
            description = "가장 최근 10개의 채팅리스트를 가져옵니다."
    )
    @GetMapping("/v1/chatlist")
    public ChatListResponse chat_list(
            @RequestParam @Valid String from,
            @RequestParam @Valid String to
    ) {
        return chatService.chat_list(from, to);
    }
}
