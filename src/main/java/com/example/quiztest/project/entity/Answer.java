package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.AnswerRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Answer extends BaseEntity {
    private String answer;
    private Boolean answerRight = false;
    @ManyToOne
    private Question question;

    public static Answer create(AnswerRequest request,Question question) {
        Answer answer = new Answer();
        answer.setAnswer(request.getAnswer());
        answer.setAnswerRight(request.getAnswerRight());
        answer.setQuestion(question);
        return answer;
    }
}
