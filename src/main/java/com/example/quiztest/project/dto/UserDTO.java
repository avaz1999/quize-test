package com.example.quiztest.project.dto;

import com.example.quiztest.project.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private UserRole role;
}
