package com.example.quiztest.project.exception;

import com.example.quiztest.project.dto.BaseMessage;
import com.example.quiztest.project.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
@NoArgsConstructor
@Getter
@Setter
public abstract class QuizTestException extends RuntimeException{
    private String message;
    private Integer code;

    public QuizTestException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public QuizTestException(String message) {
        super(message);
        this.message = message;
    }

    public abstract ErrorCode errorType();

    protected Object[] getErrorMessageArguments() {
        return null;
    }

    public BaseMessage getErrorMessage(ResourceBundleMessageSource errorMessageSource) {
        return new BaseMessage(
                errorType().getCode(),
                errorMessageSource.getMessage(
                        errorType().toString(),
                        getErrorMessageArguments(),
                        new Locale(LocaleContextHolder.getLocale().getLanguage())
                )
        );
    }
}