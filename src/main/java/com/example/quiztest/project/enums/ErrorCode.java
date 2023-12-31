package com.example.quiztest.project.enums;

public enum ErrorCode {
    VALIDATION_ERROR(100),
    USER_NOT_FOUND(101),
    NOT_ALLOWED(102),
    TYPE_MISMATCH_ERROR(103),
    CATEGORY_NOT_FOUND(200),
    ANSWER_SIZE_EXCEPTION(300),
    ANSWER_NOT_FOUND(301),
    RIGHT_ANSWER_LIMIT(301),
    QUESTION_NOT_FOUND(400),
    OBJECT_IS_NULL(500),
    QUIZ_NOT_FOUND(600);
    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
