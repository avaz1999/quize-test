package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface AnswerService {
    ApiResponse<?> create(AnswerRequest request);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable);
}
