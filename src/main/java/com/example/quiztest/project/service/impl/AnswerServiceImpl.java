package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.exception.AnswerNotFoundException;
import com.example.quiztest.project.exception.QuestionNotFoundException;
import com.example.quiztest.project.repositories.AnswerRepository;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.service.AnswerService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;

    public AnswerServiceImpl(AnswerRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    public ApiResponse<?> create(Long questionId, AnswerRequest request) {
        Question question = checkQuestion(questionId);
        Answer answer = new Answer();
        answer.setAnswer(request.getAnswer());
        answer.setAnswerRight(request.getAnswerRight() != null ? request.getAnswerRight() : false);
        answer.setQuestion(question);
        repository.save(answer);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> edit(Long id, AnswerRequest request) {
        Answer answer = checkAnswer(id);
        answer.setAnswer(request.getAnswer() != null ? request.getAnswer() : answer.getAnswer());
        answer.setAnswerRight(request.getAnswerRight() != null ? request.getAnswerRight() : answer.getAnswerRight());
        repository.save(answer);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        Answer answer = checkAnswer(id);
        answer.setDeleted(true);
        answer.setUpdatedAt(LocalDateTime.now());
        repository.save(answer);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    private Answer checkAnswer(Long id) {
        if (id == null) throw new AnswerNotFoundException();
        Answer answer = repository.findByIdAndDeletedFalse(id);
        if (answer == null) throw new AnswerNotFoundException();
        return answer;
    }

    private Question checkQuestion(Long questionId) {
        if (questionId == null) throw new QuestionNotFoundException();
        Question question = questionRepository.findByIdAndDeletedFalse(questionId);
        if (question == null) throw new QuestionNotFoundException();
        return question;
    }
}
