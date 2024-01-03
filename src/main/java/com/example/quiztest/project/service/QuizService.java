package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.CheckRequest;
import com.example.quiztest.project.dto.QuizRequest;
import org.springframework.data.domain.Pageable;


public interface QuizService {
    ApiResponse<?> create(QuizRequest request);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable);
    ApiResponse<?> getAllByCategory(String categoryName,Pageable pageable);

    ApiResponse<?> takeTest(Long quizId);

    ApiResponse<?> checkQuiz(Long quizId, CheckRequest request);
}
