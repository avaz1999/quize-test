package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.dto.QuestionRequest;
import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.exception.*;
import com.example.quiztest.project.repositories.AnswerRepository;
import com.example.quiztest.project.repositories.CategoryRepository;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.service.QuestionService;
import com.example.quiztest.project.utils.ResponseMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        repository.save(question);
        List<Answer> answers = request.getAnswers().stream()
                .map(answer -> Answer.create(answer, question))
                .toList();
        answerRepository.saveAll(answers);
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
    public ApiResponse<?> getAll(Pageable pageable, String categoryName) {
        Page<Question> questionList = repository.findAllByFilter(categoryName,pageable);
        if (questionList == null || questionList.isEmpty()) {
            return new ApiResponse<>(false, ResponseMessage.NO_DATA);
        } else {
            List<QuestionRequest> questionRequests = questionList.stream()
                    .map(question -> {
                        List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(question.getId());
                        return QuestionRequest.toDto(question, answers);
                    })
                    .toList();
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, questionRequests);
        }
    }

    @Override
    public ApiResponse<?> edit(Long id, QuestionRequest request) {
        Question question = checkQuestion(id);
        Question edit = Question.edit(question, request);
        repository.save(edit);
        return new ApiResponse<>(true,ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        Question question = checkQuestion(id);
        List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(question.getId());
        answers.forEach(answer -> answer.setDeleted(true));
        question.setDeleted(true);
        answerRepository.saveAll(answers);
        repository.save(question);
        return new ApiResponse<>(true,ResponseMessage.SUCCESS);
    }

    private boolean hasCorrectAnswer(List<AnswerRequest> answers) {
        return answers.stream().anyMatch(AnswerRequest::getAnswerRight);
    }
    private Question checkQuestion(Long id) {
        if (id == null) throw new ObjectIsNullException();
        Question question = repository.findByIdAndDeletedFalse(id);
        if (question == null) throw new QuestionNotFoundException();
        return question;
    }
}
