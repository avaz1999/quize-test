package com.example.quiztest.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private String titile;
    @NotBlank
    private String answer;
}
