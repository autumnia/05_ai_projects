package com.autumnia.chatserver.auth.controller;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="user login response")
public record LoginResponse(
        @Schema(description = "성공 유무")
        String desccription,

        @Schema(description = "jwt token")
        String token
) {
}
