package com.autumnia.chatserver.chat.service;

import com.autumnia.chatserver.chat.controller.ChatListResponse;
import com.autumnia.chatserver.chat.domain.entity.ChatEntity;
import com.autumnia.chatserver.chat.domain.model.Message;
import com.autumnia.chatserver.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional(transactionManager ="createChatTransactionManager")
    public void save_chat_message(Message message) {
        ChatEntity chatEntity = ChatEntity.builder()
                .sender(message.getFrom())
                .receiver(message.getTo())
                .message(message.getMessage())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();
        chatRepository.save(chatEntity);
    }

    public ChatListResponse chat_list(String from, String to) {
        List<ChatEntity> chats = chatRepository.fingTop10BySenderOrReceiverOrderByIdDesc(from, to);
        List<Message> messages = chats.stream()
                .map(
            chat -> new Message( chat.getSender(), chat.getReceiver(), chat.getMessage() )
                )
                .toList();

        return new ChatListResponse(messages);
    }
}
