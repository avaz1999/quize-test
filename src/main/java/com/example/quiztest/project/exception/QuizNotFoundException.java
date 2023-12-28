package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class QuizNotFoundException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.QUIZ_NOT_FOUND;
    }
}
