package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.*;
import com.example.quiztest.project.entity.*;
import com.example.quiztest.project.enums.QuizStatus;
import com.example.quiztest.project.enums.UserRole;
import com.example.quiztest.project.exception.CategoryNotFountException;
import com.example.quiztest.project.exception.QuizNotFoundException;
import com.example.quiztest.project.repositories.*;
import com.example.quiztest.project.service.QuizService;
import com.example.quiztest.project.utils.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final UserTestResultRepository userTestResultRepository;
    private final AnswerRepository answerRepository;

    public QuizServiceImpl(QuizRepository repository, UserRepository userRepository, QuestionRepository questionRepository, CategoryRepository categoryRepository, UserTestResultRepository userTestResultRepository, AnswerRepository answerRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.userTestResultRepository = userTestResultRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    @Transactional
    public ApiResponse<?> create(QuizRequest request, Short pageSize) {
        User user = new User();
        user.setFullName(request.getUserFullName());
        String username;
        username = generateUsername();
        while (!userRepository.existsByUsernameAndDeletedFalse(username)) {
            username = generateUsername();
        }
        user.setUsername(username);
        String password = generatePassword();
        user.setPassword(password);
        user.setRole(UserRole.USER);
        Category category = categoryRepository.findByNameAndDeletedFalse(request.getCategory().getName());
        if (category == null) throw new CategoryNotFountException();
        Quiz quiz = Quiz.create(request, pageSize, category);
        userRepository.save(user);
        repository.save(quiz);
        UserTestResult result = UserTestResult.create(quiz, user);
        userTestResultRepository.save(result);
        QuizeResponse quizRequest = QuizeResponse.toDto(user, quiz, request);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, quizRequest);
    }


    @Override
    public ApiResponse<?> getOne(Long id) {
        if (id == null) throw new QuizNotFoundException();
        Quiz quiz = repository.findByIdAndDeletedFalse(id);
        if (quiz == null) throw new QuizNotFoundException();
        QuizRequest quizRequest = QuizRequest.toDto(quiz);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, quizRequest);
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        Page<Quiz> quizList = repository.findAllByDeletedFalse(pageable);
        List<QuizRequest> requests = quizList.stream().map(QuizRequest::toDto).toList();
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, requests);
    }

    @Override
    public ApiResponse<?> getAllByCategory(String categoryName, Pageable pageable) {
        Category category = categoryRepository.findByNameAndDeletedFalse(categoryName);
        Page<Quiz> quizzes = repository.findAllByCategoryAndDeletedFalse(category, pageable);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, quizzes);
    }

    @Override
    @Transactional
    public ApiResponse<?> takeTest(Long quizId) {
        if (quizId == null) throw new QuizNotFoundException();
        Quiz quiz = repository.findByIdAndDeletedFalse(quizId);
        if (quiz == null) throw new QuizNotFoundException();
        List<Question> quizForUser = questionRepository
                .getForQuiz(quiz.getCategory().getName(), quiz.getQuestionSize());
        List<QuestionForTakeTestResponse> responses = quizForUser
                .stream()
                .map(question -> {
                    List<AnswerResponse> answerResponse = answerRepository
                            .findAllByQuestionIdAndDeletedFalse(question.getId())
                            .stream()
                            .map(AnswerResponse::toDto)
                            .toList();
                    return QuestionForTakeTestResponse.toDto(question, answerResponse);
                })
                .toList();
        quiz.setStatus(QuizStatus.IN_PROSES);
        quiz.setQuestions(quizForUser);
        repository.save(quiz);
        TakeTestResponse take = TakeTestResponse.toDto(quiz, responses);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, take);
    }

    @Override
    public ApiResponse<?> checkQuiz(Long quizId, CheckRequest request) {
        if (quizId == null) throw new QuizNotFoundException();
        Quiz quiz = repository.findByIdAndDeletedFalse(quizId);
        if (quiz == null) throw new QuizNotFoundException();
        if (!quiz.getStatus().equals(QuizStatus.IN_PROSES)) throw new QuizNotFoundException();
        Objects.equals(quiz.getQuestions(),request.getQuestionRequests());
        return null;
    }

    private String generateUsername() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder username = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(letters.length());
            username.append(letters.charAt(index));
        }
        return username.toString();
    }

    private String generatePassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}
