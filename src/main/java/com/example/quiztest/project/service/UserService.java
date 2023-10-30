package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.UserDTO;
import org.springframework.data.domain.Pageable;


public interface UserService {
    ApiResponse<?> getAll(Pageable pageable);

    ApiResponse<?> getOne(Long id);

    ApiResponse<?> create(UserDTO dto);
}
