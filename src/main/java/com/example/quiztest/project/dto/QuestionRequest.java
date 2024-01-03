package com.example.quiztest.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequest {
    private Long id;
    private String titile;
    private String answer;
}
