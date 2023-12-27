package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class ObjectIsNullException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.OBJECT_IS_NULL;
    }
}
