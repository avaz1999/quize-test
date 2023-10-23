package com.example.quiztest.project.entities;

import com.example.quiztest.project.entities.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}