package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.UserDTO;
import com.example.quiztest.project.entity.User;
import com.example.quiztest.project.exception.UserNotFoundException;
import com.example.quiztest.project.repositories.UserRepository;
import com.example.quiztest.project.service.UserService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        List<User> userList = repository.findAllByDeletedFalse(pageable);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO dto = User.toEntity(user);
            userDTOList.add(dto);
        }
        return new ApiResponse<>(true,ResponseMessage.SUCCESS,userDTOList);
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
        return new ApiResponse<>(true,ResponseMessage.SUCCESS,userDTO);
    }

    @Override
    public ApiResponse<?> create(UserDTO dto) {
        return null;
    }
}
