package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequest {
    private Long id;
    private String title;
    private Difficulty difficulty;
    private Category category;
    private List<AnswerRequest> answers;
}
