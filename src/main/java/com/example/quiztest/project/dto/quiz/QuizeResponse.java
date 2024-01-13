package com.example.quiztest.project.dto.quiz;

import com.example.quiztest.project.entity.Quiz;
import com.example.quiztest.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizeResponse {
    private String userFullName;
    private String username;
    private String password;
    private Long categoryId;
    private Short questionCount;

    public static QuizeResponse toDto(User user, Quiz quiz, QuizRequest quizRequest) {
        QuizeResponse request = new QuizeResponse();
        request.setUserFullName(request.userFullName);
        request.setCategoryId(quizRequest.getCategoryId());
        request.setQuestionCount(quiz.getQuestionSize());
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());
        return request;

    }
}
