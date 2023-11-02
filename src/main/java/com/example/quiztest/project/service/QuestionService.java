package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuestionDTO;
import com.example.quiztest.project.dto.QuestionEditDTO;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    ApiResponse<?> getAll(Pageable pageable);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> create(QuestionDTO dto);
    ApiResponse<?> edit(Long id, QuestionEditDTO dto);

    ApiResponse<?> delete(Long id);
}
