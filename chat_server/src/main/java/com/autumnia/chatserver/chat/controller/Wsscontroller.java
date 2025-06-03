package com.autumnia.chatserver.chat.controller;

import com.autumnia.chatserver.chat.domain.model.Message;
import com.autumnia.chatserver.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class Wsscontroller {
    private final ChatService chatService;

    @MessageMapping("/chat/message/{from}")
    @SendTo("sub/chat")
    public Message receivedMessage(@DestinationVariable String from, Message message ) {
        log.info( "from: {}, to: {}, message: {}", from, message.getTo(), message.getMessage() );

        chatService.save_chat_message(message);

        return message;
    }
}
