package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class UserNotFoundException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.USER_NOT_FOUND;
    }
}
