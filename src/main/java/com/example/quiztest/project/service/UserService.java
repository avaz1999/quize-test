package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.user.UserDTO;
import com.example.quiztest.project.dto.user.UserRequest;
import org.springframework.data.domain.Pageable;


public interface UserService {
    ApiResponse<?> create(UserDTO dto);

    ApiResponse<?> getAll(Pageable pageable);

    ApiResponse<?> getOne(Long id);

    ApiResponse<?> checkUser(UserRequest request);
}
