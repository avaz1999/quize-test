package com.example.quiztest.project.service;

import com.example.quiztest.project.dto.UserDto;
import com.example.quiztest.project.entities.User;

import java.util.List;

public interface UserService  {
    void create(UserDto dto);
    String edit(Long id, UserDto dto);
    List<UserDto> getAll();
    UserDto getOne(Long id);
    void delete(Long id);
}
