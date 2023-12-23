package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuestionRequest;

import java.awt.print.Pageable;

public interface QuestionService {
    ApiResponse<?> create(QuestionRequest request,Long categoryId);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable);
}
