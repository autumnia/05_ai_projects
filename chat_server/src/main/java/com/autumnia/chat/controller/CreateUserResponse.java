package com.autumnia.chat.controller;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="user response")
public record CreateUserResponse(
        @Schema(description = "성공 유무")
        String code
) {
}
