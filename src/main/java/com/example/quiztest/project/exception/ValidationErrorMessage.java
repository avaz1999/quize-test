package com.example.quiztest.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public record ValidationErrorMessage(int code, String message, Map<String, Object> fields) {
    public ValidationErrorMessage(int code, String message, Map<String, Object> fields) {
        this.code = code;
        this.message = message;
        this.fields = fields;
    }
}
