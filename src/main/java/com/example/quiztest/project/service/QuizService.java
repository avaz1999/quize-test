package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuizRequest;

import java.awt.print.Pageable;

public interface QuizService {
    ApiResponse<?> create(QuizRequest request);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable);
    ApiResponse<?> getAllByCategory();

}
