package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.*;
import com.example.quiztest.project.dto.answer.AnswerResponse;
import com.example.quiztest.project.dto.question.QuestionForTakeTestResponse;
import com.example.quiztest.project.dto.quiz.QuizRequest;
import com.example.quiztest.project.dto.quiz.QuizeResponse;
import com.example.quiztest.project.entity.*;
import com.example.quiztest.project.enums.Difficulty;
import com.example.quiztest.project.enums.QuizStatus;
import com.example.quiztest.project.enums.UserRole;
import com.example.quiztest.project.exception.CategoryNotFountException;
import com.example.quiztest.project.exception.QuizNotFoundException;
import com.example.quiztest.project.projection.GetQuesrionForQuiz;
import com.example.quiztest.project.repositories.*;
import com.example.quiztest.project.service.QuizService;
import com.example.quiztest.project.utils.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final UserTestResultRepository userTestResultRepository;
    private final AnswerRepository answerRepository;
    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuizServiceImpl(QuizRepository repository, UserRepository userRepository, QuestionRepository questionRepository, CategoryRepository categoryRepository, UserTestResultRepository userTestResultRepository, AnswerRepository answerRepository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.userTestResultRepository = userTestResultRepository;
        this.answerRepository = answerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public ApiResponse<?> create(QuizRequest request) {
        User user = new User();
        user.setFullName(request.getUserFullName());
        String username;
        username = generateUsername();
        while (userRepository.existsByUsernameAndDeletedFalse(username)) {
            username = generateUsername();
        }
        user.setUsername(username);
        String password = generatePassword();
        user.setPassword(password);
        user.setRole(UserRole.USER);
        Category category = categoryRepository.findByIdAndDeletedFalse(request.getCategoryId());
        if (category == null) throw new CategoryNotFountException();
        Quiz quiz = Quiz.create(request, category);
        userRepository.save(user);
        quiz.setUser(user);
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
        if (quizId == null) {
            throw new QuizNotFoundException();
        }
        Quiz quiz = repository.findByIdAndDeletedFalse(quizId);
        if (quiz == null) throw new QuizNotFoundException();

        List<GetQuesrionForQuiz> questionFor = questionRepository.getForQuiz(
                quiz.getCategory().getName(), quiz.getQuestionSize());
        List<QuestionForTakeTestResponse> responses = questionFor.stream()
                .map(question -> {
                    List<AnswerResponse> answerResponses = answerRepository
                            .findAllByQuestionIdAndDeletedFalse(question.getId())
                            .stream()
                            .map(AnswerResponse::toDto)
                            .toList();
                    return QuestionForTakeTestResponse.toDto(question, answerResponses);
                })
                .toList();

        quiz.setStatus(QuizStatus.IN_PROSES);
        List<Question> questions = questionFor.stream()
                .map(
                        i -> new Question(
                                i.getId(),
                                i.getTitle(),
                                quiz.getCategory(),
                                Difficulty.valueOf(i.getDifficulty()))).collect(Collectors.toList());
        Collections.shuffle(questions);
        quiz.setQuestions(questions);
        repository.save(quiz);

        TakeTestResponse take = TakeTestResponse.toDto(quiz, responses);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, take);
    }

    @Override
    public ApiResponse<?> checkQuiz(Long quizId, CheckRequest request) {
        if (quizId == null) throw new QuizNotFoundException();

        Quiz quiz = repository.findByIdAndDeletedFalse(quizId);
        if (quiz == null || !quiz.getStatus().equals(QuizStatus.IN_PROSES)) throw new QuizNotFoundException();
        int count = (int) quiz.getQuestions().stream()
                .flatMap(question -> request.getQuestionRequests().stream()
                        .filter(questionRequest -> question.getId().equals(questionRequest.getId()))
                        .flatMap(questionRequest ->
                                answerRepository.findAllByQuestionIdAndDeletedFalse(question.getId()).stream()
                                        .filter(Answer::getAnswerRight)
                                        .filter(answer -> answer.getAnswer().equals(questionRequest.getAnswer()))
                        )
                )
                .count();
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, count);
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
