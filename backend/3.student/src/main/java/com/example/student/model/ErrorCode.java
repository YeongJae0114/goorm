package com.example.student.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    OK(2000, "OK", HttpStatus.OK),
    SERVER_ERROR(501, "SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(5001, "BAD_REQUEST", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
