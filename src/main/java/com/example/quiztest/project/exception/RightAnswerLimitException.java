package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class RightAnswerLimitException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.RIGHT_ANSWER_LIMIT;
    }
}
