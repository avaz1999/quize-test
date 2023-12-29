package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerResponse {
    private Long id;
    private String answer;

    public static AnswerResponse toDto(Answer answer) {
        AnswerResponse response = new AnswerResponse();
        response.setId(answer.getId());
        response.setAnswer(answer.getAnswer());
        return response;
    }
}
