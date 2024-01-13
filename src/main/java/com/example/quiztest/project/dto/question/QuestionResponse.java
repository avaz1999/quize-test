package com.example.quiztest.project.dto.question;

import com.example.quiztest.project.dto.answer.AnswerRequest;
import com.example.quiztest.project.dto.category.CategoryForQuestionRequest;
import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Question;
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
    private String difficulty;
    private CategoryForQuestionRequest category;
    private List<AnswerRequest> answers;

    public static QuestionResponse toDto(Question q, List<Answer> answers) {
        List<AnswerRequest> answerRequestList = answers.stream().map(AnswerRequest::toDto).toList();
        CategoryForQuestionRequest categoryRequest = CategoryForQuestionRequest.toDto(q.getCategory());
        QuestionResponse request = new QuestionResponse();
        request.setId(q.getId());
        request.setTitle(q.getTitle());
        request.setDifficulty(q.getDifficulty().toString());
        request.setCategory(categoryRequest);
        request.setAnswers(answerRequestList);
        return request;
    }
}
