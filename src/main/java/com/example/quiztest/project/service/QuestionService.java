package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuestionResponse;
import org.springframework.data.domain.Pageable;


public interface QuestionService {
    ApiResponse<?> create(QuestionResponse request, Long categoryId);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable, String categoryName);

    ApiResponse<?> edit(Long id, QuestionResponse request);

    ApiResponse<?> delete(Long id);
}
