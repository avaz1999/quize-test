package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class QuestionNotFoundException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.QUESTION_NOT_FOUND;
    }
}
