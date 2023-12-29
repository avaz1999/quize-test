package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuizResponse;
import com.example.quiztest.project.dto.UserDTO;
import com.example.quiztest.project.dto.UserRequest;
import com.example.quiztest.project.entity.User;
import com.example.quiztest.project.entity.UserTestResult;
import com.example.quiztest.project.enums.QuizStatus;
import com.example.quiztest.project.enums.UserRole;
import com.example.quiztest.project.exception.UserNotFoundException;
import com.example.quiztest.project.repositories.UserRepository;
import com.example.quiztest.project.repositories.UserTestResultRepository;
import com.example.quiztest.project.service.UserService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserTestResultRepository userTestResultRepository;

    public UserServiceImpl(UserRepository repository, UserTestResultRepository userTestResultRepository) {
        this.repository = repository;
        this.userTestResultRepository = userTestResultRepository;
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        List<User> userList = repository.findAllByDeletedFalse(pageable);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO dto = User.toEntity(user);
            userDTOList.add(dto);
        }
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, userDTOList);
    }

    @Override
    public ApiResponse<?> getOne(Long id) {
        User user = repository.findByIdAndDeletedFalse(id);
        if (user == null) throw new UserNotFoundException();
        var userDTO = UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, userDTO);
    }

    @Override
    public ApiResponse<?> create(UserDTO dto) {
        var user = User.builder()
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(UserRole.USER)
                .build();
        repository.save(user);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> checkUser(UserRequest request) {
        User user = repository.findByUsernameAndDeletedFalse(request.getUsername());
        if (user == null) throw new UserNotFoundException();
        List<QuizResponse> responses = userTestResultRepository.findAllByUserAndDeletedFalse(user)
                .stream()
                .map(result -> {
                    QuizResponse response = new QuizResponse();
                    response.setUserId(user.getId());
                    response.setQuizId(result.getQuiz().getId());
                    response.setUserFullName(user.getFullName());
                    response.setCategory(result.getQuiz().getCategory().getName());
                    response.setQuestionSize(result.getNumberOfTests());
                    response.setTime(result.getQuiz().getTime());
                    return response;
                })
                .toList();
        return new ApiResponse<>(true, ResponseMessage.SUCCESS,responses);
    }
}
