package com.team3.musicpicky.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //Common
    SUCCESS(200, "SUCCESS", "통신에 성공했습니다."),

    //User
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "해당 유저가 존재하지 않습니다."),
    INVALID_USER(400, "INVALID_USER", "비밀번호가 일치하지 않습니다"),
    DUPLICATED_USERNAME(400, "DUPLICATED_USERNAME", "중복된 유저네임 입니다."),
    PASSWORDS_NOT_MATCHED(400, "PASSWORDS_NOT_MATCHED", "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    LOGIN_REQUIRED(400, "LOGIN_REQUIRED", "로그인이 필요합니다."),

    //Token
    INVALID_TOKEN(400, "INVALID_TOKEN", "Token이 유효하지 않습니다."),
    TOKEN_NOT_FOUND(400, "TOKEN_NOT_FOUND", "존재하지 않는 Token 입니다."),
    NOT_LOGIN_STATE(400, "NOT_LOGIN_STATE", "로그인 상태가 아닙니다."),

    NOT_PASS_VALIDATION(400, "NOT_PASS_VALIDATION", "유효성 검사를 통과하지 못했습니다.");

    private final int status;
    private final String code;
    private final String message;

}
