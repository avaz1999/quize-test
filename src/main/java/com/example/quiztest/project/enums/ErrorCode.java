package com.example.quiztest.project.enums;

public enum ErrorCode {
    VALIDATION_ERROR(100),
    USER_NOT_FOUND(101),
    NOT_ALLOWED(102),
    TYPE_MISMATCH_ERROR(103);
    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
