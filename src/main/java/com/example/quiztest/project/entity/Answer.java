package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.AnswerRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
    private Boolean right = false;
    @OneToOne
    private Question question;

    public static Answer create(AnswerRequest request,Question question) {
        Answer answer = new Answer();
        answer.setAnswer(request.getAnswer());
        answer.setRight(request.getRight());
        answer.setQuestion(question);
        return answer;
    }
}
