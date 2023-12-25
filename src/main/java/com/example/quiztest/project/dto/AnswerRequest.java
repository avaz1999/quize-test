package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Answer;
import com.example.quiztest.project.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnswerRequest {
    private Long id;
    private String answer;
    private Boolean answerRight = false;
    private Question question;

    public static AnswerRequest toDto(Answer answer) {
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setId(answerRequest.getId());
        answerRequest.setAnswer(answer.getAnswer());
        answerRequest.setAnswerRight(answer.getAnswerRight());
        answerRequest.setQuestion(answer.getQuestion());
        return answerRequest;
    }
}
