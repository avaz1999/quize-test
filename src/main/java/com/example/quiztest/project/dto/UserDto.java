package com.example.quiztest.project.dto;

import com.example.quiztest.project.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
