package com.team3.musicpicky.global.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    //User
    MEMBER_NOT_FOUND(404, "MEMBER_NOT_FOUND", "해당 유저가 존재하지 않습니다."),
    INVALID_MEMBER(400, "INVALID_MEMBER", "비밀번호가 일치하지 않습니다"),
    DUPLICATED_NICKNAME(400, "DUPLICATED_NICKNAME", "중복된 닉네임 입니다."),
    PASSWORDS_NOT_MATCHED(400, "PASSWORDS_NOT_MATCHED", "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    LOGIN_REQUIRED(400, "LOGIN_REQUIRED", "로그인이 필요합니다."),

    //Token
    INVALID_TOKEN(400, "INVALID_TOKEN", "Token이 유효하지 않습니다."),
    TOKEN_NOT_FOUND(400, "TOKEN_NOT_FOUND", "존재하지 않는 Token 입니다."),
    NOT_LOGIN_STATE(400, "NOT_LOGIN_STATE", "로그인 상태가 아닙니다."),

    NOT_PASS_VALIDATION(400, "NOT_PASS_VALIDATION", "유효성 검사를 통과하지 못했습니다."),

    POST_NOT_FOUND(404, "POST_NOT_FOUND", "게시글이 존재하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;

}
