package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Quiz;
import com.example.quiztest.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateQuizRequest {
    private String userFullName;
    private String username;
    private String password;
    private String categoryName;
    private Short questionCount;

    public static CreateQuizRequest toDto(User user, Quiz quiz, QuizRequest quizRequest) {
        CreateQuizRequest request = new CreateQuizRequest();
        request.setUserFullName(request.userFullName);
        request.setCategoryName(quizRequest.getCategory().getName());
        request.setQuestionCount(quiz.getQuestionSize());
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());
        return request;

    }
}
