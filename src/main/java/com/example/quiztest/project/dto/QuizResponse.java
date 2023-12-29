package com.example.quiztest.project.dto;

import com.example.quiztest.project.enums.QuizStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizResponse {
    private Long userId;
    private Long quizId;
    private String userFullName;
    private Short questionSize;
    private Long time;
    private String category;
    private QuizStatus status;
}
