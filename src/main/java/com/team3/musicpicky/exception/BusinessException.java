package com.team3.musicpicky.exception;

import com.team3.musicpicky.global.error.ErrorCode;
import lombok.Getter;

/* 공통 비즈니스 에러를 위한 익셉션 */
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}