package com.autumnia.chatserver.auth.controller;

import com.autumnia.chatserver.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="chat API", description="V1 chat API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
        summary = "새로운 유저를 생성합니다.",
        description = "새로운 유저생성"
    )
    @PostMapping("/v1/create-user")
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest ) {
        return authService.create_user(createUserRequest);
    }

    @Operation(
            summary = "로그인 처리.",
            description = "로그인을 진행합니다."
    )
    @PostMapping("/v1/login")
    public LoginResponse login_user(@RequestBody @Valid LoginRequest loginRequest ) {
        return authService.login(loginRequest);
    }

    @Operation(
            summary = "사용자 이름 가저오기.",
            description = "token 기반으로 유저를 가져옵니다."
    )
    @GetMapping("/v1/get-user-name/{token}")
    public String get_user_from_token(@PathVariable("token") String token) {
        return authService.get_user_from_token(token);
    }
}
