package com.example.quiztest.project.exception;

import com.example.quiztest.project.dto.BaseMessage;
import com.example.quiztest.project.enums.ErrorCode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers {
    private final ResourceBundleMessageSource errorMessageSource;

    public ExceptionHandlers(ResourceBundleMessageSource errorMessageSource) {
        this.errorMessageSource = errorMessageSource;
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleException(Exception e) {
        Map<String, Object> fields ;
        if (e instanceof QuizTestException) {
            return ResponseEntity
                    .badRequest()
                    .body(((QuizTestException) e)
                            .getErrorMessage(errorMessageSource));
        } else if (e instanceof BindException) {
            fields = new HashMap<>();
            for (FieldError fieldError : ((BindException) e).getBindingResult().getFieldErrors()) {
                fields.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
            String message = errorMessageSource
                    .getMessage(
                            errorCode.toString(),
                            null,
                            new Locale(LocaleContextHolder
                                    .getLocale().getLanguage()));
            return ResponseEntity
                    .badRequest()
                    .body(new ValidationErrorMessage(errorCode.getCode(), message, fields));

        } else if (e instanceof AccessDeniedException) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseMessage(ErrorCode.NOT_ALLOWED.getCode(),
                            errorMessageSource
                                    .getMessage(
                                            ErrorCode.NOT_ALLOWED.toString(),
                                            null,
                                            new Locale(LocaleContextHolder.getLocale().getLanguage()))));

        } else if (e instanceof HttpMessageNotReadableException) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseMessage(ErrorCode.VALIDATION_ERROR.getCode(),
                            errorMessageSource
                                    .getMessage(
                                            ErrorCode.VALIDATION_ERROR.toString(),
                                            null,
                                            new Locale(LocaleContextHolder.getLocale().getLanguage()))));

        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseMessage(ErrorCode.TYPE_MISMATCH_ERROR.getCode(),
                            errorMessageSource.getMessage(
                                    ErrorCode.TYPE_MISMATCH_ERROR.toString(),
                                    null,
                                    new Locale(LocaleContextHolder.getLocale().getLanguage()))));
        } else {
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity
                    .badRequest()
                    .body(new BaseMessage(-100, e.getLocalizedMessage()));
        }
    }
}
