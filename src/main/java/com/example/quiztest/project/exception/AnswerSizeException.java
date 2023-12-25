package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class AnswerSizeException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.ANSWER_SIZE_EXCEPTION;
    }
}
