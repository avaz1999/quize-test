package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Integer id;
    @NotBlank(message = "савол киритинг")
    private String questionTitle;
    @NotBlank(message = "вариант киритинг")
    private String option1;
    @NotBlank(message = "вариант киритинг")
    private String option2;
    @NotBlank(message = "вариант киритинг")
    private String option3;
    @NotBlank(message = "вариант киритинг")
    private String option4;
    @NotBlank(message = "Тўғри жавобни киритинг")
    private String rightAnswer;
    @NotBlank(message = "Қийинлик даражасини киритинг")
    private Difficulty difficulty;
    @NotBlank(message = "Категориясини киритинг")
    private Category category;
}
