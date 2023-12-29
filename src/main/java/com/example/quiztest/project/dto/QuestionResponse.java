package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionResponse {
    private Long id;
    private String title;
    private Difficulty difficulty;
    private CategoryForQuestionRequest category;
    private List<AnswerRequest> answers;

    public static QuestionResponse toDto(Question q, List<Answer> answers) {
        List<AnswerRequest> answerRequestList = answers.stream().map(AnswerRequest::toDto).toList();
        CategoryForQuestionRequest categoryRequest = CategoryForQuestionRequest.toDto(q.getCategory());
        QuestionResponse request = new QuestionResponse();
        request.setId(q.getId());
        request.setTitle(q.getTitle());
        request.setDifficulty(q.getDifficulty());
        request.setCategory(categoryRequest);
        request.setAnswers(answerRequestList);
        return request;
    }
}
