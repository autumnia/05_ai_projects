package com.autumnia.chat.controller;

import com.autumnia.chat.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="chat API", description="V1 chat API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @Operation(
        summary = "새로운 유저를 생성합니다.",
        description = "새로운 유저생성"
    )
    @PostMapping("/v1/create-user")
    public CreateUserResponse createUser( @RequestBody @Valid CreateUserRequest createUserRequest ) {
        return chatService.create_user(createUserRequest);
    }
}
