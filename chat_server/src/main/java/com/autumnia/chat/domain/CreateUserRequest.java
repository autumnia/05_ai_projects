package com.autumnia.chat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description="user request")
public record CreateUserRequest(
        @Schema(description = "유저 이름")
        @NotBlank @NotNull
        String name,

        @Schema(description = "유저 비밀번호")
        @NotBlank @NotNull
        String password
) {
}
