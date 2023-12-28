package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.CreateQuizRequest;
import com.example.quiztest.project.dto.QuizRequest;
import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.entity.Quiz;
import com.example.quiztest.project.entity.User;
import com.example.quiztest.project.enums.UserRole;
import com.example.quiztest.project.exception.CategoryNotFountException;
import com.example.quiztest.project.exception.QuizNotFoundException;
import com.example.quiztest.project.repositories.CategoryRepository;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.repositories.QuizRepository;
import com.example.quiztest.project.repositories.UserRepository;
import com.example.quiztest.project.service.QuizService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Random;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QuizServiceImpl(QuizRepository repository, QuestionRepository questionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
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
        CreateQuizRequest quizRequest = CreateQuizRequest.toDto(user, quiz, request);
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
        return null;
    }

    @Override
    public ApiResponse<?> getAllByCategory() {
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
