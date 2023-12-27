package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class AnswerNotFoundException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.ANSWER_NOT_FOUND;
    }
}
