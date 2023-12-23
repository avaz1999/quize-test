package com.example.quiztest.project.exception;

import com.example.quiztest.project.enums.ErrorCode;

public class CategoryNotFountException extends QuizTestException{
    @Override
    public ErrorCode errorType() {
        return ErrorCode.CATEGORY_NOT_FOUND;
    }
}
