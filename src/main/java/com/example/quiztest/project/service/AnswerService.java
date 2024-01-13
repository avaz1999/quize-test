package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.answer.AnswerRequest;

public interface AnswerService {
    ApiResponse<?> create(Long questionId, AnswerRequest request);

    ApiResponse<?> edit(Long id, AnswerRequest request);

    ApiResponse<?> delete(Long id);
}
