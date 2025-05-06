package com.autumnia.chatserver.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CustomException extends RuntimeException {
    private final ICode code;

    public CustomException(ICode code) {
        super(code.getMessage());
        this.code = code;
    }

    public CustomException(ICode code, String message) {
        super(code.getMessage() + message);
        this.code = code;
        log.error("custom exception: {}", message);
    }
}
