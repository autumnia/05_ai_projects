package com.autumnia.chat.service;

import com.autumnia.chat.controller.CreateUserRequest;
import com.autumnia.chat.controller.CreateUserResponse;
import com.autumnia.chat.domain.UserCredentialsEntity;
import com.autumnia.chat.domain.UserEntity;
import com.autumnia.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;

    public CreateUserResponse create_user(CreateUserRequest request)  {
        Optional<UserEntity> userEntity = userRepository.findByName(request.name());
        if (userEntity.isPresent()) {
            return new CreateUserResponse("400");
        }

        try {
            UserEntity new_user = add_user(request);
            UserCredentialsEntity credentials = add_credential(new_user, request);

            UserEntity user_created  = userRepository.save(new_user);

            return new CreateUserResponse("400");
        } catch ( Exception e) {
            log.debug(e.getMessage());
        }


        return new CreateUserResponse( "200");
    }

    private static UserEntity add_user(CreateUserRequest request) {
        return UserEntity.builder()
                .name(request.name())
                .created_at( new Timestamp(System.currentTimeMillis()) )
                .build();
    }

    private static UserCredentialsEntity add_credential(UserEntity user, CreateUserRequest request) {

        return UserCredentialsEntity.builder()
                .userEntity(user)
                .hashed_password(request.password())
                .build();
    }




}
