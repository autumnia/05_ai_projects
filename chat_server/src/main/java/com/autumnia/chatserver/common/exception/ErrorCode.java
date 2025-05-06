package com.autumnia.chatserver.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum ErrorCode implements ICode {
    SUCCESS(0, "SUCCESS"),
    USER_ALREADY_EXIST(-1, "이미 존재하는 회원입니다."),
    USER_SAVED_FAILED(-2, "유저 생성 실패"),
    NOT_EXIST(-3, "유저가 존재하지 않습니다."),
    MISMATCH_PASSWORD(-4, "정보가 일치하지 않습니다."),

    ACCESS_TOKEN_IS_NOT_EXPIRED(-200, "토큰이 만료되지 않았습니다."),
    TOKEN_IS_INVALID(-201, "토큰이 잘 못 되었습니다. "),
    TOKEN_IS_EXPIRED(-202, "토큰이 만료되었습니다. "),
    ;

    private final int code;
    private final String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }



}
