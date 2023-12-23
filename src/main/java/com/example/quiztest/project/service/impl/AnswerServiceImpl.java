package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.service.AnswerService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
@Service
public class AnswerServiceImpl implements AnswerService {
    @Override
    public ApiResponse<?> create(AnswerRequest request) {
        return null;
    }

    @Override
    public ApiResponse<?> getOne(Long id) {
        return null;
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        return null;
    }
}
