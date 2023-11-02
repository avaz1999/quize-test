package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuestionDTO;
import com.example.quiztest.project.dto.QuestionEditDTO;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.service.QuestionService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ApiResponse<?> getOne(Long id) {
        return null;
    }

    @Override
    public ApiResponse<?> create(QuestionDTO dto) {
        repository.save(Question.create(dto));
        return new ApiResponse<>(true,ResponseMessage.SUCCESS);
    }
 
    @Override
    public ApiResponse<?> edit(Long id, QuestionEditDTO dto) {
        repository.save(Question.edit(repository.findByIdAndDeletedFalse(id)));
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        Question question = repository.findByIdAndDeletedFalse(id);
        question.setDeleted(true);
        repository.save(question);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }
}
