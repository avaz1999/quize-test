package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Quiz;
import com.example.quiztest.project.enums.QuizStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizRequest {
    private Long id;
    @NotBlank
    private String userFullName;
    @NotBlank @Size(min = 10)
    private Short questionSize;
    @NotBlank
    private Long time;
    @NotBlank
    private CategoryRequest category;
    private QuizStatus status;

    public static QuizRequest toDto(Quiz quiz) {
        QuizRequest request = new QuizRequest();
        CategoryRequest categoryRequest = CategoryRequest.toDto(quiz.getCategory());
        request.setId(quiz.getId());
        request.setUserFullName(request.getUserFullName());
        request.setTime(quiz.getTime());
        request.setStatus(quiz.getStatus());
        request.setQuestionSize(quiz.getQuestionSize());
        request.setCategory(categoryRequest);
        return request;
    }
}
