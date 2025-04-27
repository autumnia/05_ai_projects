package com.autumnia.chat.service;

import com.autumnia.chat.domain.CreateUserRequest;
import com.autumnia.chat.domain.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {


    public CreateUserResponse createUser(CreateUserRequest request) {

        return new CreateUserResponse(request.name());
    }
}
