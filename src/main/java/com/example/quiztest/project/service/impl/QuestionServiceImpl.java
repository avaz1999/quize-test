package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.dto.QuestionRequest;
import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.exception.AnswerSizeException;
import com.example.quiztest.project.exception.CategoryNotFountException;
import com.example.quiztest.project.exception.QuestionNotFoundException;
import com.example.quiztest.project.exception.RightAnswerLimitException;
import com.example.quiztest.project.repositories.AnswerRepository;
import com.example.quiztest.project.repositories.CategoryRepository;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.service.QuestionService;
import com.example.quiztest.project.utils.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.rmi.server.RMIClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final CategoryRepository categoryRepository;
    private final QuestionRepository repository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(CategoryRepository categoryRepository, QuestionRepository repository, AnswerRepository answerRepository) {
        this.categoryRepository = categoryRepository;
        this.repository = repository;
        this.answerRepository = answerRepository;
    }

    @Override
    @Transactional
    public ApiResponse<?> create(QuestionRequest request, Long categoryId) {
        Category category = categoryRepository.findByIdAndDeletedFalse(categoryId);
        if (category == null) throw new CategoryNotFountException();
        if (request.getAnswers().size() < 2) throw new AnswerSizeException();
        if (!hasCorrectAnswer(request.getAnswers())) {
            throw new RightAnswerLimitException();
        }
        Question question = Question.create(request, category);
        List<Answer> answers = request.getAnswers().stream()
                .map(answer -> Answer.create(answer, question))
                .toList();
        answerRepository.saveAll(answers);
        repository.save(question);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> getOne(Long id) {
        Question question = repository.findByIdAndDeletedFalse(id);
        if (question == null) throw new QuestionNotFoundException();
        List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(question.getId());
        QuestionRequest request = QuestionRequest.toDto(question, answers);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, request);
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        List<Question> questionList = repository.findAllNotDeleted();
        List<QuestionRequest> questionRequests = questionList.stream()
                .map(question -> {
                    List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(question.getId());
                    return QuestionRequest.toDto(question, answers);
                }).toList();
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, questionRequests);
    }

    private boolean hasCorrectAnswer(List<AnswerRequest> answers) {
        return answers.stream().anyMatch(AnswerRequest::getAnswerRight);
    }
}
