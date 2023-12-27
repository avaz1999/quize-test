package com.example.quiztest.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizRequest {
    @NotBlank
    private String userFullName;
    @NotBlank @Size(min = 10)
    private Short questionSize;
    @NotBlank
    private Long time;
    @NotBlank
    private CategoryRequest category;
}
