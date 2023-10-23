package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.dto.UserDto;
import com.example.quiztest.project.entities.User;
import com.example.quiztest.project.enums.UserRole;
import com.example.quiztest.project.repositories.UserRepository;
import com.example.quiztest.project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserDto dto) {
            userRepository.save(
                new User(
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getUsername(),
                        dto.getPassword(),
                        UserRole.USER));
    }

    @Override
    public String edit(Long id, UserDto dto) {
        User user = userRepository.findByIdAndDeletedFalse(id);
        user.setFirstName(dto.getFirstName());
        user.setFirstName(dto.getLastName());
        user.setFirstName(dto.getUsername());
        user.setFirstName(dto.getPassword());
        userRepository.save(user);
        return "Successfully edited";
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> allUsers = userRepository.findAllByDeletedFalse();
        UserDto dto = new UserDto();
        for (User user : allUsers) {
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            userDtoList.add(dto);
        }
        return userDtoList;
    }

    @Override
    public UserDto getOne(Long id) {
        User user = userRepository.findByIdAndDeletedFalse(id);
        return new UserDto(user.getId(),user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findByIdAndDeletedFalse(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
