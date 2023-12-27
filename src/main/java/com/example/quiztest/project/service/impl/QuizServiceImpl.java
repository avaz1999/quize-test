package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuizRequest;
import com.example.quiztest.project.entity.Quiz;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.repositories.QuizRepository;
import com.example.quiztest.project.service.QuizService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    private final QuestionRepository questionRepository;

    public QuizServiceImpl(QuizRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    public ApiResponse<?> create(QuizRequest request) {
//        questionRepository.findAllByFilter()
        Quiz.create(request);
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

    @Override
    public ApiResponse<?> getAllByCategory() {
        return null;
    }
}
