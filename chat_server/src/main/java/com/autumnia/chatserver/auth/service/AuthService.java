package com.autumnia.chatserver.auth.service;

import com.autumnia.chatserver.common.exception.CustomException;
import com.autumnia.chatserver.common.exception.ErrorCode;
import com.autumnia.chatserver.common.security.Hasher;
import com.autumnia.chatserver.auth.controller.CreateUserRequest;
import com.autumnia.chatserver.auth.controller.CreateUserResponse;
import com.autumnia.chatserver.auth.controller.LoginRequest;
import com.autumnia.chatserver.auth.controller.LoginResponse;
import com.autumnia.chatserver.repository.entity.UserCredentialsEntity;
import com.autumnia.chatserver.repository.entity.UserEntity;
import com.autumnia.chatserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.autumnia.chatserver.common.security.JWTProvider;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final Hasher hasher;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> userEntity = userRepository.findByName(loginRequest.name());
        if ( userEntity.isEmpty()) {
            log.error("User {} not exists", loginRequest.name());
            throw new CustomException(ErrorCode.NOT_EXIST);
        }

        userEntity.map(v -> {
            String hashedValue = hasher.get_hashing_value(loginRequest.password());
            if (!v.getCredentials().get_hashed_password().equals(hashedValue)) {
                throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
            }

            return hashedValue;
        });

        String token = JWTProvider.create_refresh_token( loginRequest.name() );

        return new LoginResponse(ErrorCode.SUCCESS.getMessage(), token);

    }

    public CreateUserResponse create_user(CreateUserRequest request)  {
        Optional<UserEntity> userEntity = userRepository.findByName(request.name());
        if (userEntity.isPresent()) {
            log.error("User {} already exists", request.name());
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        try {
            UserEntity new_user = add_user(request);
            UserCredentialsEntity credentials = add_credential(new_user, request);

            UserEntity user_created  = userRepository.save(new_user);
        }
        catch ( Exception e) {
            log.debug(e.getMessage());
            throw new CustomException(ErrorCode.USER_SAVED_FAILED);
        }

        return new CreateUserResponse( ErrorCode.SUCCESS.getMessage() );
    }

    private UserEntity add_user(CreateUserRequest request) {
        return UserEntity.builder()
                .name(request.name())
                .created_at( new Timestamp(System.currentTimeMillis()) )
                .build();
    }

    private UserCredentialsEntity add_credential(UserEntity userEntity, CreateUserRequest request) {
        String hashedValue = hasher.get_hashing_value(request.password());

        return UserCredentialsEntity.builder()
                .userEntity(userEntity)
                .hashed_password(hashedValue)
                .build();
    }

    public String get_user_from_token(String token) {
        return JWTProvider.getUserFromToken(token);
    }


}
