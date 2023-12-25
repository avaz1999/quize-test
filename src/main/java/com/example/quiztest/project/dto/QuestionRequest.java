package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public static QuestionRequest toDto(Question q, List<Answer> answers) {
        List<AnswerRequest> answerRequestList = answers.stream().map(AnswerRequest::toDto).toList();
        QuestionRequest request = new QuestionRequest();
        request.setId(q.getId());
        request.setTitle(q.getTitle());
        request.setDifficulty(q.getDifficulty());
        request.setCategory(q.getCategory());
        request.setAnswers(answerRequestList);
        return null;
    }
}
