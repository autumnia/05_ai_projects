package com.autumnia.chatserver.chat.controller;

import com.autumnia.chatserver.chat.domain.model.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description="Chat List response")
public record ChatListResponse(
        @Schema(description = "return message: []")
        List<Message> result
) {

}
