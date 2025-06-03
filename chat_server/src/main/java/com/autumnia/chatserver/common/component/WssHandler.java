package com.autumnia.chatserver.common.component;

import com.autumnia.chatserver.chat.domain.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//  미사용
@Slf4j
//@Component
//@RequiredArgsConstructor
public class WssHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        try {
            String payload = message.getPayload();
            Message msg = objectMapper.readValue(payload, Message.class);

            session.sendMessage( new TextMessage(payload) );
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
